package com.coolweather.coolweatherjetpack.util

import com.coolweather.coolweatherjetpack.Function1
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 统一线程处理
 *
 * @param <T>
 * @return
</T> */
fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {    //compose简化线程
    return ObservableTransformer { observable ->
        observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * 等待几毫秒执行任务,返回Subscription，为解决多次重复触发，可调用unsubscribe()取消前一次定时器
 *
 * @param seconds
 * @param action1
 */
fun timer(millSeconds: Long, action1: Function1): Disposable {
    return Observable.timer(millSeconds, TimeUnit.MILLISECONDS)
        .compose<Any>(rxSchedulerHelper())
        .subscribe(
            Consumer { action1.call() },
            Consumer { throwable -> throwable.printStackTrace() })
}