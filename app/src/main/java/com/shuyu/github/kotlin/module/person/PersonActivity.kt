package com.shuyu.github.kotlin.module.person

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.LayoutInflaterCompat
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.mikepenz.iconics.context.IconicsLayoutInflater2
import com.shuyu.github.kotlin.di.ARouterInjectable
import com.shuyu.github.kotlin.module.ARouterAddress
import com.shuyu.github.kotlin.module.base.BaseFragmentActivity


/**
 * Created by guoshuyu
 * Date: 2018-10-24
 */
@Route(path = ARouterAddress.PersonActivity)
class PersonActivity : BaseFragmentActivity(), ARouterInjectable {

    @Autowired
    @JvmField
    var userName = ""

    companion object {
        fun gotoPersonInfo(userName: String) {
            getRouterNavigation(ARouterAddress.PersonActivity, userName).navigation()
        }

        fun getRouterNavigation(uri: String, userName: String): Postcard {
            return ARouter.getInstance()
                    .build(uri)
                    .withString("userName", userName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(layoutInflater, IconicsLayoutInflater2(delegate))
        super.onCreate(savedInstanceState)
    }

    override fun getInitFragment(): Fragment {
        return getRouterNavigation(ARouterAddress.PersonFragment, userName).navigation() as Fragment
    }

    override fun getToolBarTitle(): String = userName
}