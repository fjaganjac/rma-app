package com.example.rma_spirala

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), GameAdapter.RecyclerViewEvent {
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
    private lateinit var gamesRecyclerView: RecyclerView
    private var gamesList = GameData.getAll()
    private var previousGame = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        gamesRecyclerView = view.findViewById(R.id.game_list)
        gamesRecyclerView.layoutManager = LinearLayoutManager(activity)
        gamesRecyclerView.adapter = GameAdapter(gamesList, this)
        return view;
    }

    override fun onItemClick(position: Int) {
        val game = gamesList[position]
        HomeActivity.prev = gamesList[position].title
        previousGame = gamesList[position].title
        val selectedGameBundle = Bundle()
        selectedGameBundle.putString("game_title", game.title)
        val destination = GameDetailsFragment()
        destination.arguments = selectedGameBundle
        val navView: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav)
        if (navView != null) {
            navView.selectedItemId= R.id.gameDetailsFragment
        }
        navView?.menu?.forEach { it.isEnabled = true }
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,destination)?.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}