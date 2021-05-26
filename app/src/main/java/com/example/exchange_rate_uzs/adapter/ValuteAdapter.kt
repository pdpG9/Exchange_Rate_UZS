package com.example.exchange_rate_uzs.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exchange_rate_uzs.R
import com.example.exchange_rate_uzs.data.ValutaResponse
import com.example.exchange_rate_uzs.data.ValutaResponseItem
import com.example.exchange_rate_uzs.until.getPrecent
import com.squareup.picasso.Picasso

class ValuteAdapter : RecyclerView.Adapter<ValuteAdapter.ViewHolder>() {
    var data = ValutaResponse()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_valuta, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvItem)
        val tvItemCourseUSA: TextView = itemView.findViewById(R.id.tvItemCourseUSA)
        val tvItemCourseUz: TextView = itemView.findViewById(R.id.tvItemCourseUz)
        val tvItemCoursePersent: TextView = itemView.findViewById(R.id.tvItemCoursePersent)
        val ivItemIconPercent: ImageView = itemView.findViewById(R.id.ivItemIconPercent)
        val ivItemIconState: ImageView = itemView.findViewById(R.id.ivItemIconState)
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        val context = view.context

        @SuppressLint("SetTextI18n")
        fun bind(d: ValutaResponseItem) {
            tvTitle.text = d.Ccy
            tvItemCourseUSA.text = " " + d.Nominal
            tvItemCourseUz.text = d.Rate
            Log.d("TAG", "code - ${d.Code}|| name - ${d.Ccy}")
            val s = getPrecent(d.Rate, d.Diff)
            if (s.startsWith("-")) {
                ivItemIconState.setImageResource(R.drawable.ic_south_east)
                tvItemCoursePersent.setTextColor(Color.parseColor("#ed6868"))
                tvItemCoursePersent.text = s
                ivItemIconPercent.setColorFilter(Color.parseColor("#ed6868"))

            } else {
                if (s.equals("0.0")) {
                    tvItemCoursePersent.setTextColor(Color.parseColor("#717171"))
                    tvItemCoursePersent.text = getPrecent(d.Rate, d.Diff)
                    ivItemIconState.setImageResource(R.drawable.ic_remove)
                    ivItemIconPercent.setColorFilter(Color.parseColor("#717171"))

                } else {
                    tvItemCoursePersent.setTextColor(Color.parseColor("#7cc9a4"))
                    tvItemCoursePersent.text = getPrecent(d.Rate, d.Diff)
                    ivItemIconState.setImageResource(R.drawable.ic_north_east)
                    ivItemIconPercent.setColorFilter(Color.parseColor("#7cc9a4"))
                }
            }

            when (d.Code) {
                "840" -> { Glide.with(context).load(R.drawable.usa_flag).placeholder(R.drawable.ic_defoult).into(itemImage) }
                "978" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Flag_of_Europe.svg/1280px-Flag_of_Europe.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "643" -> { Glide.with(context).load(R.drawable.russion_flag).placeholder(R.drawable.ic_defoult).into(itemImage) }
                "826" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1024px-Flag_of_the_United_Kingdom.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "392" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/japan-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "944" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/az.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "050" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/bangladesh-flag-png-xl.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "975" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Flag_of_Bulgaria.svg/800px-Flag_of_Bulgaria.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "048" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/bahrain-flag-png-xl.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "096" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/bn.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "986" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w580/br.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "933" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/by.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "124" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/canada-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "756" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/switzerland/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "156" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/china-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "192" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/cuba/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "203" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/czech-republic/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "208" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/denmark-flag-png-xl.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "012" -> { Glide.with(context).load("https://icons.iconarchive.com/icons/wikipedia/flags/1024/DZ-Algeria-Flag-icon.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "818" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/egypt/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "971" -> { Glide.with(context).load("http://1.bp.blogspot.com/-VsHrWGD2hcw/UByrlUG24YI/AAAAAAAAAAQ/6PEW8rvJhxU/s640/afghanistan.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "032" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/flag-jpg-xl-7-scaled.jpg").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "981" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Flag_of_Georgia_official.svg/1200px-Flag_of_Georgia_official.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "348" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/hungary-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "360" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/indonesia-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "376" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/israel-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "356" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/india-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "368" -> { Glide.with(context).load("http://geografiya.uz/uploads/posts/2015-05/thumbs/1430942890_flag_of_iraq.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "364" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/iran-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "352" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/iceland-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "400" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/jordan-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "036" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/flag-jpg-xl-9-scaled.jpg").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "417" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Flag_of_Kyrgyzstan.svg/800px-Flag_of_Kyrgyzstan.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "116" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/cambodia-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "410" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/south-korea-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "414" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/kuwait-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "398" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Flag_of_Kazakhstan.svg/1000px-Flag_of_Kazakhstan.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "418" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/laos/flag-800.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "422" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/lebanon-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "434" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/libya/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "504" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/morocco-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "498" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/md.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "104" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/mm.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "496" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/9/93/Flag_of_Mongolia.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "484" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Flag_of_Mexico.svg/1280px-Flag_of_Mexico.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "458" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/malaysia-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "578" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/norway/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "554" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/new-zealand/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "512" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/oman-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

                "608" -> { Glide.with(context).load("https://byvali.ru/sites/default/files/pictures/philippines/_obshaya/flag.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "586" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Parti_Keadilan_Rakyat_logo.svg/1200px-Parti_Keadilan_Rakyat_logo.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "985" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Flag_of_Poland.svg/320px-Flag_of_Poland.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "634" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/qatar-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "946" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Flag_of_Romania.svg/1280px-Flag_of_Romania.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "941" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/serbia-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "051" -> { Glide.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Flag_of_Armenia.svg/1280px-Flag_of_Armenia.svg.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "682" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/saudi-arabia-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "938" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/sudan-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "752" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/sweden-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "702" -> { Glide.with(context).load("https://cdn.countryflags.com/thumbs/singapore/flag-400.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "760" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/syria-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "764" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/thailand-flag-png-xl.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "972" -> { Glide.with(context).load("https://getflags.net/img1000/tj.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "934" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/turkmenistan-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "788" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/tunisia-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "949" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/turkey-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "980" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/ukraine-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "784" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/ae.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "858" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/uruguay-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "928" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/venezuela-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "704" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/vietnam-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "960" -> { Glide.with(context).load("https://valuta.exchange/img/flags/xdr-flag.jpg").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "886" -> { Glide.with(context).load("https://flagpedia.net/data/flags/w1600/ye.png").placeholder(R.drawable.ic_defoult).into(itemImage) }
                "710" -> { Glide.with(context).load("https://www.countryflags.com/wp-content/uploads/south-africa-flag-png-large.png").placeholder(R.drawable.ic_defoult).into(itemImage) }

            }


        }
    }

}