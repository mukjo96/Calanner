package navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calanner.AddplanActivity
import com.example.calanner.R
import kotlinx.android.synthetic.main.fragment_monthly.view.*

class MonthlyFragment : Fragment(){
    var fragmentView : View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_monthly,container,false)

        fragmentView?.monthly_add_plan_btn?.setOnClickListener {
            startActivity(Intent(activity, AddplanActivity::class.java))
        }

        return fragmentView


    }
}
