/**
 * borrow idea and codes from
 *
 * https://github.com/bumptech/glide/blob/master/library/src/main/java/com/bumptech/glide/manager/RequestManagerRetriever.java
 */
package com.github.satoshun.sample.getviewfragmentsample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View

fun View.findAttachFragment(): Fragment? {
  val activity = context as? FragmentActivity ?: return null
  val allFragments = findAllFragments(activity.supportFragmentManager.fragments)

  val root = activity.findViewById<View>(android.R.id.content)
  var result: Fragment? = null
  var current = this
  while (current != root) {
    result = allFragments.firstOrNull { it.view == current }
    if (result != null) break
    current = current.parent as? View ?: break
  }
  return result
}

private fun findAllFragments(
    fragments: List<Fragment?>?
): List<Fragment> {
  if (fragments == null || fragments.isEmpty()) return emptyList()
  val fragments = fragments.filter { it?.view != null }.filterNotNull()
  return fragments + findAllFragments(fragments.map { it.childFragmentManager.fragments }.flatten())
}
