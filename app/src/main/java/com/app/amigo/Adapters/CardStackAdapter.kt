package com.app.amigo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.amigo.R

import android.content.Intent
import com.app.amigo.Activities.PersonalProfile.PersonalProfileActivity
import com.app.amigo.Models.Trends.Userlist.TrendsDatum
import com.squareup.picasso.Picasso
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import java.util.ArrayList


class CardStackAdapter(
//    private val userlistInterface: UserlistInterface?,
    private var activity: FragmentActivity?,
    private var spots: ArrayList<TrendsDatum>
) :

    RecyclerView.Adapter<CardStackAdapter.ViewHolder>(), View.OnClickListener {
    private lateinit var manager: CardStackLayoutManager


    private var viewClick: OnViewClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        userlistInterface?.userlist()
        val spot = spots[position]
//        CSPreferences.putString(activity, Utils.ANOTHERUSERID, spots.get(position).id);
        viewClick?.onitemclick(position);

        if (spot.getProfileImageName().isEmpty()) {
            holder.img_user.setImageResource(R.drawable.userdummy)
        } else {
            Picasso.with(activity).load(spot.getProfileImageName())
                .into(holder.img_user)
        }

//        Picasso.with(activity).load(spot.profileImageName).placeholder(R.drawable.userdummy) .into(holder.img_user)

        holder.tv_name.setText(spot.name)
        holder.tv_description.setText(spot.degreeOfEduction)
        holder.tv_distance.setText(spot.dob)
//        holder.img_user.setOnClickListener(this)
        holder.img_user.setOnClickListener {

//            var id: String = spots.get(position).toString()

            val intent = Intent(activity, PersonalProfileActivity::class.java)
            intent.putExtra("anotherId", spots.get(position).id)
            intent.putExtra("Setting", "trends")

            activity?.startActivity(intent)


        } // click event


//        holder.img_user?.setOnClickLispackage com.app.amigo.Adapters

//        holder.im.text = "${spot.id}. ${spot.name}"
//        holder.name.text = spot.name
//        holder.city.text = spot.city
//        holder.imging.setImageResource(spot.img)
//        Glide.with(activity)
//            .load(spot.url)
//            .into(holder.item_image)

//        Picasso.with(activity).load("http://i.imgur.com/DvpvklR.png").into(holder.item_image);
//
//        val url_image = spots.get(position).url
//        Log.e("ascbc",url_image)

//        Picasso.get()
//            .load(url_image)
//            .into(holder.item_image);


    }

    public interface OnViewClick {

        fun onitemclick(position: Int)
    }

    fun ViewClickListner(click: OnViewClick) {
        this.viewClick = click
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: ArrayList<TrendsDatum>) {
        this.spots = spots
    }

    fun getSpots(): List<TrendsDatum> {
        return spots
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img_user: ImageView = view.findViewById(R.id.img_user)
        val tv_name: TextView = view.findViewById(R.id.tv_name)
        val tv_description: TextView = view.findViewById(R.id.tv_description)
        var tv_distance: TextView = view.findViewById(R.id.tv_distance)


    }

    override fun onClick(v: View?) {


//        Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
        TODO("Not yet implemented")
    }

}

