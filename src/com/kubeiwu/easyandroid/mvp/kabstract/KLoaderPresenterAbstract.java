package com.kubeiwu.easyandroid.mvp.kabstract;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.kubeiwu.easyandroid.mvp.view.ISimpleLoaderView;

public   class KLoaderPresenterAbstract<T> extends KPresenter<ISimpleLoaderView<T>, T> implements LoaderCallbacks<T> {

	public void loadData(LoaderManager loaderManager, Bundle bundle) {
		loaderManager.initLoader(presenterId, bundle, this);
	}

	public void restartLoadData(LoaderManager loaderManager, Bundle bundle) {
		loaderManager.restartLoader(presenterId, bundle, this);
	}

	@Override
	public Loader<T> onCreateLoader(int arg0, Bundle bundle) {
		onShowLoading();
		return iView.onCreateLoader(arg0, bundle);
	}

	@Override
	public void onLoadFinished(Loader<T> arg0, T arg1) {
		onDeliverResult(arg1);
		onHideLoading();
	}

	@Override
	public void onLoaderReset(Loader<T> arg0) {

	}

	public void destroyData(LoaderManager loaderManager) {
		loaderManager.destroyLoader(presenterId);
	}
 
	@Override
	protected void onShowLoading() {
		iView.showLoading(presenterId);
	}
 
	@Override
	protected void onHideLoading() {
		iView.hideLoading(presenterId);
	}
 
	@Override
	protected void onHandleError(String errorDesc) {

	}
	/**
     * @hide
     */
	@Override
	protected void onDeliverResult(T result) {
		iView.deliverResult(presenterId, result);
	}

}
