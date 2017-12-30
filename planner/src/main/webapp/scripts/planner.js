function initCalendar() {
	console.log("MMMSJDSJDBbbbbJHDIUHkhiuhiu");
$(document).ready(function() {
    // page is now ready, initialize the calendar...
    $('#calendar').fullCalendar({
		header : { 
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		fixedWeekCount: false,
		weekNumbersWithinDays: true,
		weekNumbers: true,
		buttonText : {
			today:    'Heute',
			month:    'Monat',
			week:     'Woche',
			day:      'Tag'
		},
		defaultView: 'month',
		editable: true
    });
});
}