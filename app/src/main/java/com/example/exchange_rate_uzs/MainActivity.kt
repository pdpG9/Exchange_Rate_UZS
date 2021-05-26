package com.example.exchange_rate_uzs

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.exchange_rate_uzs.adapter.ValuteAdapter
import com.example.exchange_rate_uzs.data.ValutaResponse
import com.example.exchange_rate_uzs.data.ValutaResponseItem
import com.example.exchange_rate_uzs.until.ValutaContract
import com.example.exchange_rate_uzs.until.ValutaPresenter
import com.example.exchange_rate_uzs.until.getPrecent

class MainActivity : AppCompatActivity(), ValutaContract.ValutaView {
    private var presenter: ValutaPresenter? = null
    private var adapter: ValuteAdapter? = null
    private var valutaData: ValutaResponse? = null
    private var ivMore: ImageView? = null
    private var ivIconValuta: ImageView? = null
    private var tvCourseValuta: TextView? = null
    private var tvCoursePoint: TextView? = null
    private var ivIconDataMin: ImageView? = null
    private var tvCourseData: TextView? = null
    private var tvCourseValutaMin: TextView? = null
    private var tvCoursePersentMin: TextView? = null
    private var ivIconPercentMin: ImageView? = null
    private var ivIconState: ImageView? = null
    private var listValuta: RecyclerView? = null
    private var swipeContenier: SwipeRefreshLayout? = null
    private var visibleLayout: LinearLayout? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_layout)
        var isVisible = true
        adapter = ValuteAdapter()
        showProgress()
        loadData()
        loadView()
        loadDataToView()
        ivMore?.setOnClickListener {
            if (isVisible) {
                isVisible = false
                ivMore!!.setImageResource(R.drawable.ic_to_top)
                visibleLayout?.visibility = View.GONE
            } else {
                ivMore!!.setImageResource(R.drawable.ic_to_bottom)
                isVisible = true
                visibleLayout?.visibility = View.VISIBLE
            }
        }

        swipeContenier?.setOnRefreshListener {
            presenter?.getCurrentData()
        }
    }

    private fun loadDataToView() {
        if (valutaData == null) {
            showProgress()

        } else {
            hideProgress()
            adapter?.data = valutaData!!
            listValuta?.adapter = adapter
            listValuta?.layoutManager = LinearLayoutManager(this)
            loadMainValute(valutaData!![0])
        }
        hideProgress()
    }

    private fun loadMainValute(item: ValutaResponseItem) {
        var base = ""
        var point = ""
        var isP = false

        fun getCourseValutaMin(item: String): String {
            var b = ""
            var p = ""
            var isP1 = false
            item.forEach { it ->
                if (it != '.' && !isP1) {
                    b += it
                } else {
                    isP1 = true
                }
                if (isP) {
                    p += it
                }
            }
            return b + "." + p.substring(0, 2)
        }
        item.Rate.forEach { it ->
            if (it != '.' && !isP) {
                base += it
            } else {
                isP = true
            }
            if (isP) {
                point += it
            }
        }
        Log.d("TAG", "loadMainValute: base = ${base}")
        Log.d("TAG", "loadMainValute: point = $point")
        val n: Double = item.Rate.toDouble()
        val p: Double = item.Diff.toDouble()
        val k = n - p
        val f = (p / k) * 100
        //o`zgartirildi
        tvCourseValuta?.text = base
        Log.d("TAG", "loadMainValute: item.Rate ${item.Rate} ")
        Log.d("TAG", "loadMainValute: item.Diff ${item.Diff} ")
        Log.d("TAG", "loadMainValute: item.Date ${item.Date} ")
        tvCourseValutaMin?.text = getCourseValutaMin(k.toString()) //k.toString()
        tvCourseData?.text = item.Date
        tvCoursePoint?.text = point
        tvCoursePersentMin?.text = getPrecent(item.Rate, item.Diff)
        if (f < 0) {
            ivIconState?.setImageResource(R.drawable.ic_south_east)
            ivIconPercentMin?.setColorFilter(Color.parseColor("#ed6868"))
            tvCoursePersentMin?.setTextColor(Color.parseColor("#ed6868"))
        } else {
            ivIconState?.setImageResource(R.drawable.ic_north_east)
            ivIconPercentMin?.setColorFilter(Color.parseColor("#7cc9a4"))
            tvCoursePersentMin?.setTextColor(Color.parseColor("#7cc9a4"))
        }
    }

    private fun loadData() {
        presenter = ValutaPresenter(this)
        presenter?.getCurrentData()
    }


    private fun loadView() {
        visibleLayout = findViewById(R.id.visibleLayout)
        ivMore = findViewById(R.id.ivMore)
        ivIconValuta = findViewById(R.id.ivIconValuta)
        tvCourseValuta = findViewById(R.id.tvCourseValuta)
        tvCoursePoint = findViewById(R.id.tvCoursePoint)
        ivIconDataMin = findViewById(R.id.ivIconDataMain)
        tvCourseData = findViewById(R.id.tvCourseData)
        tvCourseValutaMin = findViewById(R.id.tvCourseValutaMin)
        tvCoursePersentMin = findViewById(R.id.tvCoursePersentMin)
        ivIconPercentMin = findViewById(R.id.ivIconPercentMin)
        ivIconState = findViewById(R.id.ivIconState)
        listValuta = findViewById(R.id.listValuta)
        swipeContenier = findViewById(R.id.swipeContenier)
    }

    override fun getValuteResponse(valutaResponse: ValutaResponse) {
        valutaData = valutaResponse
        hideProgress()
        loadDataToView()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {
        swipeContenier?.isRefreshing = false
    }

    override fun showMessage(mess: String) {


    }
}
