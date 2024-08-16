package test.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{

	int count = 0;
	int maxRetry = 0; //no retries for now.
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		while(count<maxRetry) {
			count++;
			return true;
		}
		return false;
		
	}

}
