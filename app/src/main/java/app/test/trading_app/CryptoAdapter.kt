package app.test.trading_app;

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlin.random.Random

class CryptoAdapter(
    private val context: Context,
    private val cryptoList: ArrayList<CryptoModal>,
    val performance: String = "hour"
) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())
    private var isAutoScrollEnabled = false
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itme_ad_layout, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val cryptoItem = cryptoList[position]

        holder.coinSymbol.text = cryptoItem.symbol
        var persent = 30f
        if (performance == "hour") {
            if (cryptoItem.performanceHour != "0") {
                persent = cryptoItem.performanceHour.toFloat() * 25
            } else {
                persent = 5f
            }
        } else if (performance == "month") {
            if (cryptoItem.performanceMonth != "0") {
                persent = cryptoItem.performanceMonth.toFloat() * 25
            } else {
                persent = 5f
            }
        } else if (performance == "year") {
            if (cryptoItem.performanceYear != "0") {
                persent = cryptoItem.performanceYear.toFloat() * 25
            } else {
                persent = 5f
            }
        } else if (performance == "day") {
            if (cryptoItem.performanceDay != "0") {
                persent = cryptoItem.performanceDay.toFloat() * 25
            } else {
                persent = 5f
            }
        }
        if (persent < 0) {
            persent *= -1
        }
        if (persent > 50) {
            persent = (persent * 0.6).toFloat()
        }
        setSquareImageViewSizeByPercentage(holder.plane, context, persent, holder.coinSymbol)

        holder.itemView.setOnClickListener {
            showCustomDialog(cryptoItem)
        }
    }

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun updateTrans(updateTrans: ArrayList<CryptoModal>) {
        cryptoList.clear()
        cryptoList.addAll(updateTrans)

        notifyDataSetChanged()

    }

    fun setSquareImageViewSizeByPercentage(
        View: ConstraintLayout,
        context: Context,
        percentage: Float,
        textView: TextView
    ) {
        val displayMetrics = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as? android.view.WindowManager)?.defaultDisplay?.getMetrics(
            displayMetrics
        )

        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        val size =
            (percentage / 100) * if (screenWidth < screenHeight) screenWidth else screenHeight

        val layoutParams = View.layoutParams as RecyclerView.LayoutParams
        layoutParams.width = size.toInt()
        layoutParams.height = size.toInt()

        View.layoutParams = layoutParams
        val textSize = (percentage / 100) * 48 // Adjust the multiplier as needed
        textView.textSize = textSize
        val animator = ObjectAnimator.ofFloat(View, "translationY", 0f, 12f, 0f)
        animator.duration = 1200 + Random.nextLong(1, 600)// Duration in milliseconds
        animator.repeatCount = ObjectAnimator.INFINITE // Infinite loop
        animator.interpolator = LinearInterpolator() // Linear interpolator for smooth motion

        // Start the animation
        animator.start()
    }

    fun smoothScrollToRandomPosition() {
        var itemCount = cryptoList.size
        if (itemCount > 0) {
            itemCount = 90
            val randomPosition = Random.nextInt(itemCount)
            recyclerView.smoothScrollToPosition(randomPosition)
        }

    }

    private fun showCustomDialog(coin: CryptoModal) {
        val dialog = Dialog(context, R.style.TransparentDialogTheme)
        dialog.setContentView(R.layout.popup)

        val btnShowAds = dialog.findViewById<MaterialCardView>(R.id.ok)
        val howtocard = dialog.findViewById<MaterialCardView>(R.id.howto)
        val nameTextView = dialog.findViewById<TextView>(R.id.name)
        val priceTextView = dialog.findViewById<TextView>(R.id.price)
        val lastHourTextView = dialog.findViewById<TextView>(R.id.lasthour)
        val lastDayTextView = dialog.findViewById<TextView>(R.id.lastday)
        val lastMonthTextView = dialog.findViewById<TextView>(R.id.lastmonth)
        val lastYearTextView = dialog.findViewById<TextView>(R.id.lastyear)
        val darkGreenColor = Color.rgb(0, 100, 0)
        nameTextView.text = "Currency Name : ${coin.name} (${coin.symbol})"
        val formattedPrice = String.format("%.4f", coin.price.toFloat())
        priceTextView.text = "Price : $formattedPrice USD."
        // Convert performance values to floats (assuming they are strings)
        val performanceDay = coin.performanceDay?.toFloatOrNull() ?: 0f
        val performanceHour = coin.performanceHour.toFloatOrNull() ?: 0f
        val performanceMonth = coin.performanceMonth.toFloatOrNull() ?: 0f
        val performanceYear = coin.performanceYear.toFloatOrNull() ?: 0f

// Set text and color for lastDayTextView
        lastDayTextView.text = "${performanceDay}%"
        lastDayTextView.setTextColor(if (performanceDay > 0) darkGreenColor  else Color.RED)

// Set text and color for lastHourTextView
        lastHourTextView.text = "${performanceHour}%"
        lastHourTextView.setTextColor(if (performanceHour > 0) darkGreenColor  else Color.RED)

// Set text and color for lastMonthTextView
        lastMonthTextView.text = "${performanceMonth}%"
        lastMonthTextView.setTextColor(if (performanceMonth > 0) darkGreenColor  else Color.RED)

// Set text and color for lastYearTextView
        lastYearTextView.text = "${performanceYear}%"
        lastYearTextView.setTextColor(if (performanceYear > 0) darkGreenColor  else Color.RED)


        btnShowAds.setOnClickListener {
            dialog.dismiss()
        }

        howtocard.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=is+${coin.name}+a+good+investment+")
            )
            context.startActivity(intent);
        }

        dialog.show()
    }

    fun startAutoScroll() {
        isAutoScrollEnabled = true
        autoScrollRunnable.run()
    }

    // Add a method to stop automatic scrolling
    fun stopAutoScroll() {
        isAutoScrollEnabled = false
        handler.removeCallbacks(autoScrollRunnable)
    }

    // Define a Runnable for automatic scrolling
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            if (isAutoScrollEnabled) {
                smoothScrollToRandomPosition()
                // Schedule the next scroll after five seconds
                handler.postDelayed(this, 5000)
            }
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coinSymbol: TextView = itemView.findViewById(R.id.coin)
        val plane: ConstraintLayout = itemView.findViewById(R.id.plane)

        // Add other views as needed
    }
}
