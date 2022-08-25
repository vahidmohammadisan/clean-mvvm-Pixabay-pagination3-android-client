package group.payback.pixabayclient.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import group.payback.pixabayclient.R
import group.payback.pixabayclient.databinding.FragmentImageListBinding
import group.payback.pixabayclient.ui.image.adapter.ImageAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageListFragment : Fragment(), OnItemClickListener {

    private lateinit var _binding: FragmentImageListBinding
    private val binding get() = _binding
    val viewmodel by viewModels<ImageViewModel>()
    lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.searchImage("fruits")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)

        setupList()

        viewmodel.image.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                imageAdapter.submitData(it)
            }
        }

        binding.viewmodel = viewmodel

        return binding.root
    }

    private fun setupList() {
        imageAdapter = ImageAdapter(this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = imageAdapter
        }
    }

    override fun onItemClicked(imageId: Int) {
        if (findNavController().currentDestination?.id == R.id.imageListFragment) {
            val action =
                ImageListFragmentDirections.actionImageListFragmentToDetailsDialogFragment(
                    imageId.toString()
                )
            findNavController().navigate(action)
        }
    }

}