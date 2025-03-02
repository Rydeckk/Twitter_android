package com.example.twitter_like.views.pager_fragments.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.auth0.android.jwt.JWT
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.data.request.user.UpdateUserRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory

class UpdateUserDialogFragment(private val onDialogClosed: () -> Unit) :
    DialogFragment() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(requireContext()))
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.edit_user_dialog)
        fetchUser(dialog)
        return dialog
    }

    private fun getToken(): String? {
        val sharedPreferences =
            requireContext().getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }

    private fun userViewHolder(user: User, dialog: Dialog) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return

        val firstnameInput = dialog.findViewById<EditText>(R.id.dialog_edit_firstname)
        firstnameInput.setText(user.firstname)
        val lastnameInput = dialog.findViewById<EditText>(R.id.dialog_edit_lastname)
        lastnameInput.setText(user.lastname)
        val usernameInput = dialog.findViewById<EditText>(R.id.dialog_edit_username)
        usernameInput.setText(user.username)
        val biographyInput = dialog.findViewById<EditText>(R.id.dialog_edit_biography)
        biographyInput.setText(user.biography)

        val onSubmit = dialog.findViewById<Button>(R.id.dialog_edit_submit)

        onSubmit.text = "Save"

        onSubmit.setOnClickListener {
            userViewModel.updateUserById(
                UpdateUserRequest(
                    firstnameInput.text.toString(),
                    lastnameInput.text.toString(),
                    usernameInput.text.toString(),
                    biographyInput.text.toString()
                ),
                userId,
                object : GenericCallback<Boolean> {
                    override fun onSuccess(data: Boolean) {
                        onDialogClosed()
                        dismiss()
                        Toast.makeText(
                            requireContext(),
                            "User updated successfull !",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    override fun onError(error: String) {
                        dismiss()
                        Toast.makeText(
                            requireContext(),
                            "Error, try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }

    private fun fetchUser(dialog: Dialog) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return

        userViewModel.getUserById(userId, object : GenericCallback<User> {
            override fun onSuccess(data: User) {
                userViewHolder(data, dialog)
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }
        })
    }
}