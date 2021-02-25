package com.qdd.mvxdemo.fragment

import android.util.Log
import androidx.fragment.app.Fragment

abstract class AbstractLazyFragment : Fragment() {

    /**
     * 生命周期：
     * setUserVisibleHint()
     * ->onAttach()
     * -> onCreate()
     * -> onCreateView()
     * -> onViewCreated()
     * -> onActivityCreate()
     * ->  onStart()
     * -> onResume()
     *
     * setUserVisibleHint的调用时机总是在 初始化时调用，可见时调用，由可见转换成不可见时调用。
     */

    /**
     * 数据是否已加载
     */
    private var isLoaded = false

    /**
     * Fragment是否对用户可见
     */
    private var isVisibleToUser = false

    /**
     * fragment是否初始化
     */
    private var isFragmentCreated = false

    /**
     * 子类实现
     */
    abstract fun lazyInit()

    private fun judgeLazyInit() {
        /**
         * 1、当数fragment已初始化、数据未加载、并且当前fragment对用户可见执行-->数据加载操作
         * 2、并将数据加载状态改为true
         */
        if (!isLoaded && isVisibleToUser && isFragmentCreated) {
            lazyInit()
            Log.d("AbstractLazyFragment", "lazyInit:!!!!!!!")
            isLoaded = true
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        judgeLazyInit()
    }

    override fun onResume() {
        super.onResume()
        isFragmentCreated = true
        judgeLazyInit()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isVisibleToUser = !hidden
        judgeLazyInit()
    }

    //在Fragment销毁View的时候，重置状态
    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
        isVisibleToUser = false
        isFragmentCreated = false
    }

}
