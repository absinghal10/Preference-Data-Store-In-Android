package com.example.preferencedatastoreexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class LetterListFragment : Fragment() {

    lateinit var alphabetRecylerView: RecyclerView
    val alphabetList= mutableListOf<Char>()
    private var isLinearLayoutManager=true
    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_letter_list, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        alphabetList.clear()

        settingsDataStore = SettingsDataStore(requireContext())

        for( i in 'A'..'Z'){
            alphabetList.add(i)
        }

        alphabetRecylerView=view.findViewById(R.id.alphabet_recylerview)

        settingsDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner, { value ->
            isLinearLayoutManager = value
            chooseLayout()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Launch a coroutine and write the layout setting in the preference Datastore
                lifecycleScope.launch {
                    settingsDataStore.saveLayoutToPreferencesStore(!isLinearLayoutManager, requireContext())
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            alphabetRecylerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            alphabetRecylerView.layoutManager = GridLayoutManager(requireContext(), 4)
        }
        alphabetRecylerView.adapter = LetterAdapter(requireContext(),alphabetList)
    }

}