package Portal.fragmenti.admin

import Portal.a257.R
import Portal.a257.databinding.AdminOdobriSportBinding
import Portal.adapter.SportAdapter
import Portal.viewModel.SportViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

class AdminOdobriSport: Fragment(R.layout.admin_odobri_sport) {

    private val mSportViewModel: SportViewModel by viewModels()
    private lateinit var binding: AdminOdobriSportBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AdminOdobriSportBinding.bind(view)

        //RecyclerView
        val adapter = SportAdapter()
        val recyclerOdobriSport = binding.recyclerViewOdobriSport
        recyclerOdobriSport.adapter = adapter
        recyclerOdobriSport.layoutManager = LinearLayoutManager(requireContext())

        //RasporedViewModel
        mSportViewModel.readAllDataSport.observe(viewLifecycleOwner, Observer { sport ->
            adapter.setData(sport)
        })
    }
}