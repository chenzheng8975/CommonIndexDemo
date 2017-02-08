/** 
 * Copyright © 2015. All rights reserved.
 *
 * @Package: cn.ymex.cute.toolbox
 * @Description: TODO
 * @author: ymex email:ymex@foxmail.com
 * @date: 2015-4-9 下午11:47:53
 * @version: V1.0
 */
package com.czhappy.commonindexdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @ClassName: Searcher
 * @Description: TODO 查找view （注解 后加）
 * @author: ymex
 * @date: 2015-4-9 下午11:47:53
 */
public class Finder {
	/**
	 * @Title: find
	 * @Description: find view by id from view
	 * @param view
	 * @param id
	 * @return
	 * @return: T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T find(View view, int id) {
		return (T) view.findViewById(id);
	}

	/**
	 * @Title: find
	 * @Description: TODO find view by id from activity
	 * @param act
	 * @param id
	 * @return
	 * @return: T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T find(Activity act, int id) {
		return (T) act.findViewById(id);
	}

	/**
	 * @Description加载布局文件
	 * @param context 
	 * @param resource
	 * @return
	 */
	public static View inflate(Context context, int resource) {
		return (View) LayoutInflater.from(context).inflate(resource, null);
	}

}
