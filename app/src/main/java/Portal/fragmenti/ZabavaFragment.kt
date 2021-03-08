package Portal.fragmenti

import Portal.a257.R
import Portal.adapter.ZabavaAdapter
import Portal.viewModel.ZabavaViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.zabava_fragment.*
import kotlinx.android.synthetic.main.zabava_fragment.view.*

class ZabavaFragment: Fragment() {

    private lateinit var mZabavaViewModel: ZabavaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.zabava_fragment,container,false)

        //RecyclerView
        val adapter = ZabavaAdapter()
        val recyclerZabava = view.recyclerViewZabava
        recyclerZabava.adapter = adapter
        recyclerZabava.layoutManager = LinearLayoutManager(requireContext())

        //RasporedViewModel
        mZabavaViewModel = ViewModelProvider(this).get(ZabavaViewModel::class.java)
        mZabavaViewModel.readAllDataZabava.observe(viewLifecycleOwner, Observer { zabava ->
            adapter.setData(zabava)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewZabava.addItemDecoration(DividerItemDecoration
            (recyclerViewZabava.context,DividerItemDecoration.VERTICAL))

    }

}