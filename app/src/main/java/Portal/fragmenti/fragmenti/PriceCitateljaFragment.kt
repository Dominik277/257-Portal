package Portal.fragmenti.fragmenti

import Portal.MainActivity
import Portal.a257.R
import Portal.a257.databinding.PriceCitateljaFragmentBinding
import Portal.adapter.PriceCitateljaAdapter
import Portal.model.PriceCitateljaTable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PriceCitateljaFragment : Fragment(R.layout.price_citatelja_fragment) {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("priceCitatelja")
    var priceCitateljaAdapter: PriceCitateljaAdapter? = null
    private lateinit var binding: PriceCitateljaFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PriceCitateljaFragmentBinding.bind(view)

        binding.recyclerViewPriceCitatelja.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewPriceCitatelja.context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.floatingActionButton.setOnClickListener {
            val action = PriceCitateljaFragmentDirections.actionPriceCitateljaNavDrawerToMenuDodajNovuPricuCitatelja()
            findNavController().navigate(action)
        }

        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity){
            (activity as MainActivity?)?.showBottomNavigationView()
        }
    }

    private fun setUpRecyclerView() {
        val query: Query = collectionReference
        val tableRecyclerOptions: FirestoreRecyclerOptions<PriceCitateljaTable> =
            FirestoreRecyclerOptions.Builder<PriceCitateljaTable>()
                .setQuery(query, PriceCitateljaTable::class.java)
                .build()
        priceCitateljaAdapter = PriceCitateljaAdapter(tableRecyclerOptions)

        binding.recyclerViewPriceCitatelja.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPriceCitatelja.adapter = priceCitateljaAdapter
    }

    override fun onStart() {
        super.onStart()
        priceCitateljaAdapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        priceCitateljaAdapter!!.stopListening()
    }

}