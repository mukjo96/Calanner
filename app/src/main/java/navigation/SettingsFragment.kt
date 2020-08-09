package navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calanner.LoginActivity
import com.example.calanner.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment(){


    var fragmentView : View? = null
    var auth : FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_settings,container,false)
        auth = FirebaseAuth.getInstance()

        fragmentView?.signout_btn?.setOnClickListener {
            activity?.finish()
            startActivity(Intent(activity,LoginActivity::class.java))
            auth?.signOut()
        }


        return fragmentView
    }
}


