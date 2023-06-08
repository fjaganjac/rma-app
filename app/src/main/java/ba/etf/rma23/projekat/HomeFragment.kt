package ba.etf.rma23.projekat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.data.repositories.AccountGameRepository
import com.example.rma_spirala.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

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
    private lateinit var searchButton: Button
    private lateinit var searchBar: EditText


    private lateinit var gamesRecyclerView: RecyclerView
    private var gamesList = listOf<Game>()
    private var gamesAdapter = GameAdapter(gamesList,this)
    private var previousGame = ""
    //public var searchResult :List<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var view = inflater.inflate(R.layout.fragment_home, container, false)
        gamesRecyclerView = view.findViewById(R.id.game_list)
        searchButton = view.findViewById(R.id.search_button)
        searchBar = view.findViewById(R.id.search_query_edittext)

        searchButton.setOnClickListener {
            onClick()
        }

        /*val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            var result = AccountGameRepository.getSavedGames()
            for(item in result)
            {

            }
        }*/

        val scope = CoroutineScope(Job() + Dispatchers.IO)
        var listaIgara: MutableList<Game> = ArrayList<Game>()
        var result: List<Game>? = null
        scope.launch {
            result = AccountGameRepository.getSavedGames()
            val scope1 = CoroutineScope(Job() + Dispatchers.Main)
            scope1.launch {
                for(item in result!!) {
                    listaIgara.add(GamesRepository.getGamesByName(item.title)[0])


                }
                gamesList = listaIgara
                gamesAdapter.updateGames(listaIgara)
            }
        }

        gamesRecyclerView.layoutManager = LinearLayoutManager(activity)

        gamesRecyclerView.adapter = gamesAdapter


        return view;
    }

    override fun onItemClick(position: Int) {
        //println("ASDASDASDSADASDASDASDASDASDADADD")
        val game = gamesList[position]
        HomeActivity.prev = gamesList[position].title
        previousGame = gamesList[position].title


        val selectedGameBundle = Bundle()
        var obj = game
        //selectedGameBundle.putString("game_title", game.title)
        selectedGameBundle.putSerializable("game",obj)

        val destination = GameDetailsFragment()
        destination.arguments = selectedGameBundle
        val navView: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav)
        if (navView != null) {
            navView.selectedItemId= R.id.gameDetailsFragment
        }
        navView?.menu?.forEach { it.isEnabled = true }
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,destination)?.commit()
    }

    private fun onClick() {
        search(searchBar.text.toString())
    }


    fun search(name: String) {

        val scope = CoroutineScope(Job() + Dispatchers.IO /*+ coroutineExceptionHandler*/)
        //var listaIgara: List<Game> = ArrayList<Game>()
        var result: List<Game>? = null
        scope.launch {
            // Vrti se poziv servisa i suspendira se rutina dok se `withContext` ne zavrsi
            //print("REZULTAT ")
            val scope1 = CoroutineScope(Job() + Dispatchers.Main)
            scope1.launch {
                result = GamesRepository.getGamesByName(name)
                gamesList = result as List<Game>
                gamesAdapter.updateGames(result)
            }
        }
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