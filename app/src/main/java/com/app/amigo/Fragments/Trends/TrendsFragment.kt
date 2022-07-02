package com.app.amigo.Fragments.Trends

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.app.amigo.Adapters.CardStackAdapter
import com.app.amigo.Adapters.SpotDiffCallback
import com.app.amigo.Fragments.Trends.Presenter.AddtoFavPresenter
import com.app.amigo.Fragments.Trends.Presenter.CreateRequestPresenter
import com.app.amigo.Fragments.Trends.Presenter.TrendsPresenter
import com.app.amigo.Fragments.Trends.Presenter.UserlistPresenter
import com.app.amigo.Fragments.Trends.View.TrendsView
import com.app.amigo.Models.Trends.Userlist.TrendsDatum
import com.app.amigo.Models.Trends.Userlist.TrendsExample
import com.app.amigo.R
import com.app.amigo.Utility
import com.app.amigo.Utils.CSPreferences
import com.app.amigo.Utils.Utils
import com.yuyakaido.android.cardstackview.*
import java.util.ArrayList


class TrendsFragment() : Fragment(), CardStackListener, View.OnClickListener, TrendsView {
    lateinit var dataList: MutableList<TrendsDatum>
    private var data: MutableList<TrendsExample>? = null
    private val adapter by lazy { CardStackAdapter(activity, createSpots()) }
    private val manager by lazy { CardStackLayoutManager(activity, this) }
    private lateinit var cardStackView: CardStackView
    private lateinit var lin_exit: LinearLayout
    private lateinit var linearlayout: LinearLayout
    private lateinit var lin_retry: LinearLayout
    private lateinit var lin_star: LinearLayout
    private lateinit var lin_trends: LinearLayout
    private lateinit var lin_fav: LinearLayout
    private lateinit var img_exit: ImageView
    private lateinit var img_fav: ImageView
    private lateinit var img_retry: ImageView
    private lateinit var img_star: ImageView
    private lateinit var img_trends: ImageView

    private var position: Int = 0

    //    private var data : ArrayList<UserlistDatum> = ArrayList()
    private var gender = ""
    private lateinit var trendsPresenter: TrendsPresenter
    private lateinit var addtoFavPresenter: AddtoFavPresenter
    private lateinit var createRequestPresenter: CreateRequestPresenter
    private lateinit var userlistPresenter: UserlistPresenter
    var booleanVar = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changetheme()//                WebServices.getmInstance().otpverifcation();

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_trends, container, false)

        //        tv_facebook.setFragment(this);
        trendsPresenter = TrendsPresenter(activity, this)
        addtoFavPresenter = AddtoFavPresenter(activity, this)
        createRequestPresenter = CreateRequestPresenter(activity, this)
        userlistPresenter = UserlistPresenter(activity, this)
        linearlayout = view.findViewById(R.id.linearlayout) as LinearLayout

        cardStackView = view.findViewById(R.id.card_stack_view)
        linearlayout = view.findViewById(R.id.linearlayout) as LinearLayout
        lin_exit = view.findViewById(R.id.lin_exit) as LinearLayout
        lin_retry = view.findViewById(R.id.lin_retry) as LinearLayout
        lin_star = view.findViewById(R.id.lin_star) as LinearLayout
        lin_star = view.findViewById(R.id.lin_star) as LinearLayout
        lin_trends = view.findViewById(R.id.lin_trends) as LinearLayout
        lin_fav = view.findViewById(R.id.lin_fav) as LinearLayout
        img_exit = view.findViewById(R.id.img_exit) as ImageView
        img_fav = view.findViewById(R.id.img_fav) as ImageView
        img_retry = view.findViewById(R.id.img_retry) as ImageView
        img_star = view.findViewById(R.id.img_star) as ImageView
        img_trends = view.findViewById(R.id.img_trends) as ImageView

        setupCardStackView()
        setupButton()
        customtheme()
        listeners()
        userlistPresenter.getUserlist(adapter)



        return view
    }

    private fun listeners() {
        lin_exit.setOnClickListener(this)
        lin_retry.setOnClickListener(this)
        lin_star.setOnClickListener(this)
        lin_trends.setOnClickListener(this)
        lin_fav.setOnClickListener(this)

    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(5)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.1f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        cardStackView?.layoutManager = manager

        cardStackView?.adapter = adapter
        cardStackView?.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }


    override fun onCardDragging(direction: Direction?, ratio: Float) {
        if (direction == Direction.Right) {
            addtoFavPresenter.addtofavMethod(dataList.get(manager.topPosition).id)


            lin_fav.setBackgroundResource(R.drawable.oval_dark)
            lin_exit.setBackgroundResource(R.drawable.oval_white)
            lin_trends.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.trends))
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.lightblue
                )
            )
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.black))



            Log.e("check", "123")
        } else if (direction == Direction.Left) {

            lin_exit.setBackgroundResource(R.drawable.oval_dark)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            lin_trends.setBackgroundResource(R.drawable.oval_white)
            lin_fav.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.fav))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.lightblue))
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.trends))


        } else if (direction == Direction.Top) {
            createRequestPresenter.crearteRequestMethod(dataList.get(manager.topPosition).id)


            lin_trends.setBackgroundResource(R.drawable.oval_dark)
            lin_exit.setBackgroundResource(R.drawable.oval_white)
            lin_fav.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.fav))
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.lightblue
                )
            )


        }
//        Toast.makeText(activity, manager.topPosition.toString(), Toast.LENGTH_SHORT).show()
        if (dataList.size == manager.topPosition + 1) {
//            cardStackView.visibility = View.GONE


            val handler = Handler()
            handler.postDelayed({
                reload()
                userlistPresenter.getUserlist(adapter)
                Utils.hideDialog()

            }, 500)

        }
        Log.d("CardStackView", "onCardDragging: d = ${direction?.name}, r = $ratio")

    }


    override fun onCardSwiped(direction: Direction?) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 5) {
            paginate()
            setData(dataList)
        }
//        else if (direction==Direction.Right){
//            addtoFavPresenter.addtofavMethod(dataList.get(manager.topPosition).id)
//            Toast.makeText(activity, "favourite added successfully", Toast.LENGTH_SHORT).show()
//        }
    }


    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")


    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }


    override fun onCardAppeared(view: View?, position: Int) {
        val textView =
            view?.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView?.text}")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        val textView =
            view?.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView?.text}")
    }


    private fun paginate() {
        val old = adapter.getSpots()
        val new = old.plus(dataList)
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)

    }

    private fun setupButton() {

        lin_exit?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()

        }

    }

    private fun changetheme() {
        gender = CSPreferences.readString(activity, Utils.GENDERSELECT)
        if (gender.equals("Male")) {
            activity?.setTheme(R.style.MaleTheme)
        } else {
            activity?.setTheme(R.style.Femaletheme)

        }
    }

    private fun customtheme() {
        if (gender.equals("Male", ignoreCase = true)) {
            activity?.setTheme(R.style.Maletheme)
            linearlayout.setBackgroundDrawable(resources.getDrawable(R.drawable.menbackimage))
//            linearlayout.setBackgroundColor(resources.getColor(R.color.maleblueTheme))
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(resources.getColor(R.color.maleblueTheme))
            Utility.onActivityCreateSetTheme(activity)
        } else {
            activity?.setTheme(R.style.Femaletheme)
            linearlayout.setBackgroundDrawable(resources.getDrawable(R.drawable.twoshadebackground))

        }
    }

    private fun createSpots(): ArrayList<TrendsDatum> {
        val spots = ArrayList<TrendsDatum>()

        return spots
    }

    override fun onClick(v: View?) {
        if (v == lin_exit) {


            if (dataList.size == manager.topPosition + 1) {
//            cardStackView.visibility = View.GONE

                reload()
                userlistPresenter.getUserlist(adapter)


            }


            lin_exit.setBackgroundResource(R.drawable.oval_dark)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            lin_trends.setBackgroundResource(R.drawable.oval_white)
            lin_fav.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.fav))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.lightblue))
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.trends))
//            lin_exit?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()
//
//            }
        } else if (v == lin_retry) {
            reload()
            userlistPresenter.getUserlist(adapter)
//            setData(dataList)


            lin_retry.setBackgroundResource(R.drawable.oval_dark)
            lin_exit.setBackgroundResource(R.drawable.oval_white)
            lin_trends.setBackgroundResource(R.drawable.oval_white)
            lin_fav.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.fav))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.lightblue))
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.trends))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.black))

        } else if (v == lin_star) {
            lin_star.setBackgroundResource(R.drawable.oval_dark)
            lin_exit.setBackgroundResource(R.drawable.oval_white)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            lin_trends.setBackgroundResource(R.drawable.oval_white)
            lin_fav.setBackgroundResource(R.drawable.oval_white)
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.fav))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.trends))
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.black))
//            lin_star?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Top)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()


        } else if (v == lin_trends) {

            createRequestPresenter.crearteRequestMethod(dataList.get(manager.topPosition).id)
            Toast.makeText(activity, "request sent Successfully", Toast.LENGTH_SHORT).show()
            lin_trends.setBackgroundResource(R.drawable.oval_dark)
            lin_exit.setBackgroundResource(R.drawable.oval_white)
            lin_fav.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.fav))
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.lightblue
                )
            )
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.black))
//            lin_trends?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Top)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()



            if (dataList.size == manager.topPosition + 1) {
                lin_trends.setEnabled(false)
                img_trends.setEnabled(false)


                val handler = Handler()
                handler.postDelayed({
                    reload()
                    userlistPresenter.getUserlist(adapter)
                    Utils.hideDialog()

                }, 1500)


            }

        } else if (v == lin_fav) {
            addtoFavPresenter.addtofavMethod(dataList.get(manager.topPosition).id)
            Toast.makeText(activity, "favourite added successfully", Toast.LENGTH_SHORT).show()
            lin_fav.setBackgroundResource(R.drawable.oval_dark)
            lin_exit.setBackgroundResource(R.drawable.oval_white)
            lin_trends.setBackgroundResource(R.drawable.oval_white)
            lin_star.setBackgroundResource(R.drawable.oval_white)
            lin_retry.setBackgroundResource(R.drawable.oval_white)
            img_trends.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.trends))
            img_fav.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.white))
            img_retry.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.yellow))
            img_star.setColorFilter(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.lightblue
                )
            )
            img_exit.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.black))
//            lin_fav?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()

            if (dataList.size == manager.topPosition + 1) {
                lin_fav.setEnabled(false)
                img_fav.setEnabled(false)


                val handler = Handler()
                handler.postDelayed({
                    reload()
                    userlistPresenter.getUserlist(adapter)
                    Utils.hideDialog()

                }, 1500)
            }


        }


    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, requireActivity().fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun setData(data: MutableList<TrendsDatum>?) {

        this.dataList = data!!
        val old = adapter.getSpots()
        val new = old.plus(dataList)
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(dataList as ArrayList<TrendsDatum>)
        result.dispatchUpdatesTo(adapter)

    }

    override fun setposition(position: Int) {

        this.position = position;
    }


//    private fun removeFirst(size: Int) {
//        if (adapter.getSpots().isEmpty()) {
//            return
//        }
//
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            for (i in 0 until size) {
//                removeAt(manager.topPosition)
//            }
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
//    }
//
//    private fun removeLast(size: Int) {
//        if (adapter.getSpots().isEmpty()) {
//            return
//        }
//
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            for (i in 0 until size) {
//                removeAt(this.size - 1)
//            }
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
//    }

    private fun reload() {
        val old = adapter.getSpots()
        val new = createSpots()
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    companion object {
        @JvmStatic
        fun newInstance(): TrendsFragment {
            return TrendsFragment()
        }
    }

}