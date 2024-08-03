package com.example.mobile_final.activity.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mobile_final.R
import com.example.mobile_final.database.AppDatabase
import com.example.mobile_final.databinding.FragmentCreateTaskBinding
import com.example.mobile_final.entity.Assignment
import com.example.mobile_final.viewModel.AssignmentViewModel
import com.example.mobile_final.viewModel.TaskViewModel
import com.example.mobile_final.viewModel.factory.AssignmentViewModelFactory
import com.example.mobile_final.viewModel.factory.TaskViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar

class CreateTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentCreateTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var assignmentViewModel: AssignmentViewModel
    var itemPos: Int? = null;
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinute = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        pickDateTime()
        createButton()
        getDateTimeCalender()

        assignmentViewModel.findAllMutable()
        assignmentViewModel.assignmentList.observe(viewLifecycleOwner) {
            dropdown(it)
        }
        return binding.root
    }

    private fun setupViewModel() {
        val dao = AppDatabase.getInstance(requireActivity()).taskDao()
        val factory = TaskViewModelFactory(dao)
        taskViewModel = ViewModelProvider(this, factory)[TaskViewModel::class.java]

        val assignmentDao = AppDatabase.getInstance(requireActivity()).assignmentDao()
        val assignmentFactory = AssignmentViewModelFactory(assignmentDao)
        assignmentViewModel =
            ViewModelProvider(this, assignmentFactory)[AssignmentViewModel::class.java]
    }


    private fun dropdown(list: List<Assignment>) {
        val autoComplete: AutoCompleteTextView = binding.autoCompleteCreateTask
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.container_dropdown,
            list.map { it.name }
        )
        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                itemPos = position
            }
    }

    private fun createButton() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, saveDay)
        calendar.set(Calendar.MONTH, saveMonth)
        calendar.set(Calendar.YEAR, saveYear)

        binding.btnCreateTask.setOnClickListener {
            if (itemPos != null) {
                assignmentViewModel.assignmentList.value?.get(itemPos!!)?.let { it1 ->
                    taskViewModel.insert(
                        it1.id,
                        binding.txtTaskName.text.toString(),
                        calendar.time
                    )
                }
            }

            it.findNavController().navigate(R.id.action_createTaskFragment_to_taskFragment)
            //TODO toast
        }
    }

    private fun getDateTimeCalender() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDateTime() {
        binding.editTextDate2.setOnClickListener {
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        binding.editTextTime2.setOnClickListener {
            TimePickerDialog(requireContext(), this, hour, minute, true).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month
        saveYear = year
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, saveDay)
        calendar.set(Calendar.MONTH, saveMonth)
        calendar.set(Calendar.YEAR, saveYear)
        binding.editTextDate2.setText(SimpleDateFormat("dd-MM-yyyy").format(calendar.time))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        saveHour = hourOfDay
        saveMinute = minute

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, saveDay)
        calendar.set(Calendar.MONTH, saveMonth)
        calendar.set(Calendar.YEAR, saveYear)
        calendar.set(Calendar.HOUR, saveHour)
        calendar.set(Calendar.MINUTE, saveMinute)
        binding.editTextTime2.setText(SimpleDateFormat("HH:mm a").format(calendar.time))
    }
}