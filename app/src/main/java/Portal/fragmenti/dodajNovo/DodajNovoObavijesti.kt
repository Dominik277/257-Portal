package Portal.fragmenti.dodajNovo

import Portal.a257.R
import Portal.a257.databinding.DodajNovoObavijestiFragmentBinding
import Portal.model.ObavijestiTable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder

class DodajNovoObavijesti : Fragment(R.layout.dodaj_novo_obavijesti_fragment) {

    private val personCollectionRef = Firebase.firestore.collection("obavijest")
    private lateinit var binding: DodajNovoObavijestiFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DodajNovoObavijestiFragmentBinding.bind(view)

        binding.gumbSpremiObavijest.setOnClickListener {
            val naslov = binding.naslov.text.toString()
            val clanak = binding.clanak.text.toString()
            val vrijeme = binding.vrijeme.text.toString()
            val obavijest = ObavijestiTable(naslov, clanak, vrijeme)
            savePerson(obavijest)
        }

        binding.gumbPrikaziObavijest.setOnClickListener {
            retrievePersons()
        }

    }

    private fun savePerson(obavijest: ObavijestiTable) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectionRef.add(obavijest).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Uspješno spremljeno!", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun retrievePersons() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = personCollectionRef.get().await()
            val sb = StringBuilder()
            for (document in querySnapshot.documents) {
                val obavijest = document.toObject<ObavijestiTable>()
                sb.append("$obavijest\n")
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    /*
    @SuppressLint("SimpleDateFormat")
    private fun insertDataToDatabase() {
        val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm")
        val currentDate = sdf.format(Date())

        val noviNaslov = binding.etObavijestiNaslov.text.toString()
        val novoVrijeme = currentDate
        val noviClanak = binding.etObavijestiClanak.text.toString()
        val novaSlika = R.drawable.jaksic

        val obavijesti = ObavijestiTable(0, noviNaslov, noviClanak, novoVrijeme, novaSlika)
        mObavijestViewModel.addObavijesti(obavijesti)
    }
     */
}