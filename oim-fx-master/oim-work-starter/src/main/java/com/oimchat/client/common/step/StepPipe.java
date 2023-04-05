
package com.oimchat.client.common.step;

/**
 * Description <br>
 * Date 2021-03-10 09:06:40<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class StepPipe<R, V> {

	private StepPipe<?, ? extends R> nextStep;
	private Next<R, V> next;

	public StepPipe<?, ? extends R> step(Next<R, V> n) {
		next = n;
		nextStep = new StepPipe<>();
		return nextStep;
	}

	public void execute(V v) {
		if (null != v) {
			NextResult<R> nr = next.execute(v);
			if (null != nr) {
				// nextStep.execute(nr.data);
			}
		}
	}
}
