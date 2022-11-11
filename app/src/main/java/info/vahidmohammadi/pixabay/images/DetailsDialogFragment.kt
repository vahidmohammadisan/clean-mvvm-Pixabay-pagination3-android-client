package info.vahidmohammadi.pixabay.images

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import info.vahidmohammadi.pixabay.R
import info.vahidmohammadi.pixabay.databinding.FragmentDialogDetailBinding


@AndroidEntryPoint
class DetailsDialogFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentDialogDetailBinding
    private val binding get() = _binding
    val viewmodel by viewModels<ImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDialogDetailBinding.inflate(inflater, container, false)

        val imageId = arguments?.getString("ImageID", "0")

        binding.btnYes.setOnClickListener {

            viewmodel.getImageDetails(imageId = imageId!!.toInt())

            viewmodel.imageDetails.observe(viewLifecycleOwner) {

                if (it == null)
                    return@observe

                binding.apply {
                    questionArea.visibility = View.GONE
                    detailArea.visibility = View.VISIBLE
                    tvLike.text = it.likes.toString()
                    tvComments.text = it.comments.toString()
                    tvView.text = it.views.toString()
                    tvDownload.text = it.downloads.toString()
                    tvUseName.text = it.user.toString()
                }

                binding.imvImage.load(it.largeImageURL)

            }
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun getTheme(): Int = R.style.BaseBottomSheetDialog
}