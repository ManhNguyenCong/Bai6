package com.example.bai6.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bai6.R
import com.example.bai6.data.model.ApiUser
import com.example.bai6.databinding.DialogFragmentDetailUserBinding
import com.example.bai6.databinding.FragmentListUsersBinding
import com.example.bai6.di.ExerciseApplication
import com.example.bai6.ui.adapter.UserListAdapter
import com.example.bai6.util.TAG
import com.example.bai6.viewmodel.MyViewModel
import javax.inject.Inject

class ListUsersFragment : Fragment() {

    private lateinit var binding: FragmentListUsersBinding

    private var adapter: UserListAdapter? = null

    @Inject
    lateinit var viewModel: MyViewModel

    override fun onAttach(context: Context) {
        (activity?.application as ExerciseApplication).appComponent.inject(this)
        super.onAttach(context)
        Log.d(TAG, "onAttach: $viewModel")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListUsersBinding.bind(
            inflater.inflate(R.layout.fragment_list_users, container, false)
        )

        adapter = UserListAdapter { user ->
            DetailUserDialogFragment(user).show(
                childFragmentManager,
                DetailUserDialogFragment.TAG
            )
        }
        binding.rvUsers.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAll().observe(this.viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }
}

class DetailUserDialogFragment(private val user: ApiUser.User) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(requireContext())

            val layout = layoutInflater.inflate(R.layout.dialog_fragment_detail_user, null)
            val binding = DialogFragmentDetailUserBinding.bind(layout)

            binding.tvId.text = user.id.toString()
            binding.tvName.text = user.name.toString()
            binding.tvGender.text = user.gender
            binding.tvLocation.text = String.format(
                "%s\nCoordinates: %s\nTimezone: %s\nPostcode: %s",
                user.location.toString(),
                user.location.coordinates.toString(),
                user.location.timezone.toString(),
                user.location.postcode
            )
            binding.tvEmail.text = user.email
            binding.tvPhone.text = user.phone
            binding.tvCell.text = user.cell
            binding.tvPicture.text = user.picture.toString()
            binding.tvNat.text = user.nat
            binding.tvDOB.text = user.dob.toString()
            binding.tvRegister.text = user.registered.toString()
            binding.tvLogin.text = user.login.toString()

            builder.setView(layout)
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }

            builder.create()
        } ?: throw ActivityNotFoundException("Activity can't null")
    }

    companion object {
        const val TAG = "DetailUserDialogFragment"
    }
}