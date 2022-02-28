package com.example.androidwebapi.screens.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidwebapi.R
import com.example.androidwebapi.database.Movies
import com.example.androidwebapi.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    lateinit var binding : FragmentDetailsBinding
    lateinit var movies: Movies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val movie = DetailsFragmentArgs.fromBundle(requireArguments()).selectedMovie
        val viewModelFactory = DetailsViewModelFactory(movie, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailsViewModel::class.java)

        val t1 = "Taxi 2"//proba za bazu
        val v1 = "4.2"
        val d1 = "Neki opis"

        binding.titleField.text = t1//proba za bazu
        binding.voteField.text = v1
        binding.descField.text = d1

        movies = Movies(2, "Taxi 4", v1.toDouble(), "Neki opis 2")//proba za bazu



        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.viewModel?.saveOrUpdate(movies)
        return true
    }


}