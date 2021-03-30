package Portal.fragmenti.detailFragmenti

import Portal.a257.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.detail_vijesti_fragment.view.*

class DetailVijestiFragment : Fragment() {

    private val args by navArgs<DetailVijestiFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_vijesti_fragment, container, false)

        view.detailVijestiNaslov.setText(args.vijestiData.vijestiNaslov)
        view.detailVijestiClanak.setText(args.vijestiData.vijestiClanak)

        return view
    }

}