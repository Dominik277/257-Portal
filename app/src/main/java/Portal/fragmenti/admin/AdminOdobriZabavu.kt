package Portal.fragmenti.admin

import Portal.a257.R
import Portal.adapter.ZabavaAdapter
import Portal.viewModel.ZabavaViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.admin_odobri_zabava.*

@AndroidEntryPoint
class AdminOdobriZabavu: Fragment(R.layout.admin_odobri_zabava) {

    private val mZabavaViewModel: ZabavaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RecyclerView
        val adapter = ZabavaAdapter()
        val recyclerOdobriZabava = recyclerViewOdobriZabava
        recyclerOdobriZabava.adapter = adapter
        recyclerOdobriZabava.layoutManager = LinearLayoutManager(requireContext())

        //RasporedViewModel
        mZabavaViewModel.readAllDataZabava.observe(viewLifecycleOwner, Observer { zabava ->
            adapter.setData(zabava)
        })
    }

}