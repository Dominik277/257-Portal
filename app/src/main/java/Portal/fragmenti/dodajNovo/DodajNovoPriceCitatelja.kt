package Portal.fragmenti.dodajNovo

import Portal.a257.R
import Portal.a257.databinding.DialogCustomImageSelectionBinding
import Portal.a257.databinding.DodajNovoPriceCitateljaFragmentBinding
import Portal.model.PriceCitateljaTable
import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder

class DodajNovoPriceCitatelja : Fragment(R.layout.dodaj_novo_price_citatelja_fragment), View.OnClickListener {

    private val personCollectionRef = Firebase.firestore.collection("priceCitatelja")
    private lateinit var binding: DodajNovoPriceCitateljaFragmentBinding

    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DodajNovoPriceCitateljaFragmentBinding.bind(view)

        binding.addImagePriceCitatelja.setOnClickListener(this)

        binding.gumbSpremiPriceCitatelja.setOnClickListener {
            val naslov = binding.naslov.text.toString()
            val clanak = binding.clanak.text.toString()
            val priceCitatelja = PriceCitateljaTable(naslov, clanak)

            if (binding.naslov.text.isNullOrEmpty()){
                binding.naslov.error = "Naslov ne može biti prazan"
            }else if (binding.clanak.text.isNullOrEmpty()){
                binding.clanak.error = "Članak ne može biti prazan!"
            }else{
                savePerson(priceCitatelja)
                val action = DodajNovoPriceCitateljaDirections.actionMenuDodajNovuPricuCitateljaToPriceCitateljaNavDrawer()
                findNavController().navigate(action)
            }
        }
    }

    private fun savePerson(priceCitatelja: PriceCitateljaTable) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectionRef.add(priceCitatelja).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Uspješno spremljeno!", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.addImagePriceCitatelja -> {
                    customImageSelectionDialog()
                    return
                }
            }
        }
    }

    private fun customImageSelectionDialog() {
        val dialog = Dialog(requireContext())
        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)

        binding.tvCamera.setOnClickListener {

            Dexter.withContext(requireContext()).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                //Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, CAMERA)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    showRationaleDialogForPermissions()
                }
            })
                .onSameThread()
                .check()

            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            Dexter.withContext(requireContext()).withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    val galleryIntent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    startActivityForResult(galleryIntent, GALLERY)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Toast.makeText(
                        requireContext(), "You have denied storage permission",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    showRationaleDialogForPermissions()
                }
            })
                .onSameThread()
                .check()

            dialog.dismiss()
            dialog.dismiss()
        }

        dialog.setContentView(binding.root)
        dialog.show()
    }

}