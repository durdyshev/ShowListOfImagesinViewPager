package com.example.showlistofimagesinviewpager

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getDrawable
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

class MainActivity : AppCompatActivity() {
    //creating object of ViewPager
    private lateinit var viewPager: ViewPager
    private lateinit var textCounter:TextView
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>
    private lateinit var linearLayout: LinearLayout
    //images array
    var imagesArray = intArrayOf(
        R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
        R.drawable.a5, R.drawable.a6    )

    //Creating Object of ViewPagerAdapter
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById<View>(R.id.recycle_yeke_haly_viewpager) as ViewPager
        textCounter=findViewById(R.id.recycle_yeke_haly_surat_sanawy)
        linearLayout = findViewById(R.id.recycle_yeke_haly_linear)

        showViewPagerItems(imagesArray)

    }
    @SuppressLint("SetTextI18n")
    private fun showViewPagerItems(images: IntArray){

        mViewPagerAdapter = ViewPagerAdapter(this@MainActivity, images)
        viewPager.adapter = mViewPagerAdapter

        dotscount = mViewPagerAdapter.count
        dots = arrayOfNulls(dotscount)


        linearLayout.removeAllViews()




        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                getDrawable(
                    this,
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            params.setMargins(8, 0, 8, 0)
            linearLayout.addView(dots[i], params)
        }

        val urlText: String = images[0].toString()

        Glide.with(applicationContext)
            .asBitmap()
            .load(urlText)

            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {

                }
            })

        dots[0]!!.setImageDrawable(getDrawable(this, R.drawable.active_dot))
        textCounter.text = "1/$dotscount"
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {


            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                var i = 0
                while (i < dotscount) {
                    dots[i]!!.setImageDrawable(
                        getDrawable(
                            this@MainActivity,
                            R.drawable.non_active_dot
                        )
                    )
                    textCounter.text = (position + 1).toString() + "/" + dotscount
                    val urlText: String = images[i].toString()

                    Glide.with(applicationContext)
                        .asBitmap()
                        .load(urlText)
                        .into(object : SimpleTarget<Bitmap?>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap?>?
                            ) {

                            }
                        })
                    i++
                }
                dots[position]!!.setImageDrawable(
                    getDrawable(
                        this@MainActivity,
                        R.drawable.active_dot
                    )
                )
                textCounter.text =  (position + 1).toString() + "/" + dotscount
            }

            override  fun onPageScrollStateChanged(state: Int) {

            }
        })
    }




}