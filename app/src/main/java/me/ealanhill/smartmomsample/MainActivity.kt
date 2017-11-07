package me.ealanhill.smartmomsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import me.ealanhill.smartmomsample.posts.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swapFragments(PostsFragment())
    }

    fun swapFragments(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
        if (addToBackStack) {
            transaction.addToBackStack("contacts")
        }
        transaction.commit()
    }
}
