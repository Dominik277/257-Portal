package Portal.fragmenti.admin.odobri

import Portal.a257.R
import Portal.a257.databinding.AdminOdobriPriceCitateljaBinding
import Portal.adapter.PriceCitateljaAdapter
import Portal.adapter.adminAdapter.AdminPoljoprivredaAdapter
import Portal.adapter.adminAdapter.AdminPriceCitateljaAdapter
import Portal.viewModel.PriceCitateljaViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminOdobriPriceCitatelja: Fragment(R.layout.admin_odobri_price_citatelja) {

    private val mPriceCitateljaViewModel: PriceCitateljaViewModel by viewModels()
    private lateinit var binding: AdminOdobriPriceCitateljaBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AdminOdobriPriceCitateljaBinding.bind(view)

        //RecyclerView
        val adapter = AdminPriceCitateljaAdapter()
        val recyclerOdobriPriceCitatelja = binding.recyclerViewOdobriPriceCitatelja
        recyclerOdobriPriceCitatelja.adapter = adapter
        recyclerOdobriPriceCitatelja.layoutManager = LinearLayoutManager(requireContext())

        //RasporedViewModel
        mPriceCitateljaViewModel.readAllDataPriceCitatelja.observe(viewLifecycleOwner, Observer { priceCitatelja ->
            adapter.setData(priceCitatelja)
        })
    }

}