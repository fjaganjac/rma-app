package ba.etf.rma23.projekat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.get
import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository
import com.example.rma_spirala.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var setAgeEditText: EditText
    private lateinit var setAgeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_account, container, false)

        val navView: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav)
        var i = 0
        navView?.menu?.forEach {
            if(i==0) {
                it.isEnabled = true
            }
            i++
        }

        setAgeButton = view.findViewById(R.id.set_age_button)
        setAgeButton.setOnClickListener {
            setAge()
        }
        setAgeEditText = view.findViewById(R.id.set_age_edittext)



        //navView?.menu?.getItem(R.id.gameDetailsFragment)?.isEnabled = false
        return view
    }


    private fun setAge() {
        if(setAgeEditText.text != null) {
            var value = setAgeEditText.text.toString().toInt()
            //println("BLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+value)
            AccountGamesRepository.setAge(value)
            val toast = Toast.makeText(context, "Age changed successfully", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}