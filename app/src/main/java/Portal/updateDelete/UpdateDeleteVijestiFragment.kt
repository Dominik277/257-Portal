package Portal.updateDelete

import Portal.a257.R
import Portal.database.table.VijestiTable
import Portal.viewModel.VijestiViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.update_delete_vijesti_fragment.*
import kotlinx.android.synthetic.main.update_delete_vijesti_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateDeleteVijestiFragment: Fragment() {

    private val args by navArgs<UpdateDeleteVijestiFragmentArgs>()
    private lateinit var mVijestiViewModel: VijestiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.update_delete_vijesti_fragment,container,false)

        mVijestiViewModel = ViewModelProvider(this).get(VijestiViewModel::class.java)

        view.updateVijestiNaslov.setText(args.currentVijest.vijestiNaslov)
        view.updateVijestiClanak.setText(args.currentVijest.vijestiClanak)

        view.gumbUpdateVijesti.setOnClickListener {
            updateItemVijesti()
        }

        return view
    }

    private fun updateItemVijesti() {
        val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm")
        val currentDate = sdf.format(Date())

        val naslovVijesti = updateVijestiNaslov.text.toString()
        val clanakVijesti = updateVijestiClanak.text.toString()
        val slikaVijesti = 0
        val vrijemeVijesti = currentDate

        val updateVijesti = VijestiTable(args.currentVijest.id,naslovVijesti,clanakVijesti,vrijemeVijesti,slikaVijesti)
        mVijestiViewModel.updateVijesti(updateVijesti)
        findNavController().navigate(R.id.action_updateDeleteVijestiFragment_to_vijestiNavDrawer)
    }

}