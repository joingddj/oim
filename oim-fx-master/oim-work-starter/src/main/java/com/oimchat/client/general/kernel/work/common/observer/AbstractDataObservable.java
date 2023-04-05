
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.ListenerBox.Put;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 16:50:56<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractDataObservable<A, U, D> extends AbstractMaterial {

	ListenerBox<A> addBox = new ListenerBox<>();
	ListenerBox<U> updateBox = new ListenerBox<>();
	ListenerBox<D> deleteBox = new ListenerBox<>();

	public AbstractDataObservable(AppContext appContext) {
		super(appContext);
	}

	public void putAddListener(A e) {
		addBox.add(e);
	}

	public void putUpdateListener(U e) {
		updateBox.add(e);
	}

	public void putDeleteListener(D e) {
		deleteBox.add(e);
	}

	public void executeAdd(Put<A> p) {
		addBox.execute(p);
	}

	public void executeUpdate(Put<U> p) {
		updateBox.execute(p);
	}

	public void executeDelete(Put<D> p) {
		deleteBox.execute(p);
	}
}
