package navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calanner.AddplanActivity
import com.example.calanner.ListAdapter
import com.example.calanner.Plan
import com.example.calanner.R
import kotlinx.android.synthetic.main.fragment_weekly.*
import kotlinx.android.synthetic.main.fragment_weekly.view.*
import kotlinx.android.synthetic.main.fragment_weekly.view.weekly_list_recycler_view

class WeeklyFragment : Fragment(){
    var fragmentView : View? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_weekly,container,false)

        fragmentView?.weekly_add_plan_btn?.setOnClickListener {
            startActivity(Intent(activity, AddplanActivity::class.java))
        }

        return fragmentView


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        weekly_list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = com.example.calanner.ListAdapter(mWeeklyPlans)
        }
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) { // Adapter에 아이템 삭제 요청
                (weekly_list_recycler_view.adapter as ListAdapter).removeWeeklyPlan(viewHolder.adapterPosition)
            }
        }).apply { // ItemTouchHelper에 RecyclerView 설정
            attachToRecyclerView(weekly_list_recycler_view)
        }
    }

    companion object {

        val mWeeklyPlans = mutableListOf(
            Plan("Weekly Plan", "내용",2020,5,30,5,11)
        )
        fun newInstance(): WeeklyFragment = WeeklyFragment()
    }
}


