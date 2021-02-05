package com.toy.utilities;

import java.util.Arrays;
import java.util.Map;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import com.aventstack.extentreports.Status;
import com.toy.report.ReportTestManager;

public class Verifier extends Assertion {

	private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

	@Override
	protected void doAssert(IAssert<?> a) {
		onBeforeAssert(a);
		try {
			a.doAssert();
			onAssertSuccess(a);
			if (ReportTestManager.getTest() != null)
				ReportTestManager.getTest().log(Status.PASS, a.getMessage());
		} catch (AssertionError ex) {
			if (ReportTestManager.getTest() != null)
				ReportTestManager.getTest().log(Status.FAIL, a.getMessage());
			onAssertFailure(a, ex);
			m_errors.put(ex, a);
		} finally {
			onAfterAssert(a);
		}
	}

	public void assertAll() {
		if (!m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder("The following asserts failed:");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(",\n");
				}
				sb.append("\n\t\br");
				sb.append(ae.getKey().getMessage());
				sb.append("\nStack Trace :");
				sb.append(Arrays.toString(ae.getKey().getStackTrace()).replaceAll(",", "\n"));
			}
			if (ReportTestManager.getTest() != null) {
				ReportTestManager.getTest().error(sb.toString());
			}
		}
	}

}