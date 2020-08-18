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
import com.example.calanner.Auth
import com.example.calanner.Auth.Companion.getUid
import com.example.calanner.ListAdapter
import model.Plan
import com.example.calanner.R
import com.google.firebase.auth.FirebaseAuth
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_daily.*
import kotlinx.android.synthetic.main.fragment_weekly.*
import kotlinx.android.synthetic.main.fragment_weekly.view.*

class WeeklyFragment : Fragment(){
    var fragmentView : View? = null
    private val realm = Realm.getDefaultInstance()


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

        val realmResult: RealmResults<Plan> = realm.where<Plan>()
            .equalTo("userid", getUid())
            .equalTo("dayorweek", "weekly")
            .findAll()
            .sort("date", Sort.ASCENDING)

        val adapters = ListAdapter(realmResult) { id ->
            startActivity(Intent(activity, AddplanActivity::class.java).putExtra("id", id))
        }

        realmResult.addChangeListener { _ ->
            adapters.notifyDataSetChanged()
        }

        weekly_list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = adapters
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
                realm.beginTransaction()
                val listid: Long = realmResult[viewHolder.adapterPosition]!!.id
                (deleteTodo(listid.toInt()))
                realm.commitTransaction()
            }
        }).apply { // ItemTouchHelper에 RecyclerView 설정
            attachToRecyclerView(weekly_list_recycler_view)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun deleteTodo(id: Int) {

        val deleteItem = realm.where<Plan>().equalTo("id", id).findFirst()!!
        deleteItem.deleteFromRealm()

    }

}



