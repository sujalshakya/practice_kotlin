package base.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CustomDialog : DialogFragment() {

    interface DialogListener {
        fun onPositiveButtonClick(message: String)
    }

    private var listener: DialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = arguments?.getString(ARG_MESSAGE) ?: ""

        return AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                listener?.onPositiveButtonClick(message )
            }
            .setNegativeButton("Cancel"){ _, _ -> }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DialogListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement DialogListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val ARG_MESSAGE = "message"

        fun newInstance(message: String): CustomDialog {
            val dialog = CustomDialog()
            val args = Bundle()
            args.putString(ARG_MESSAGE, message)
            dialog.arguments = args
            return dialog
        }

        const val TAG = "CustomDialog"
    }
}
