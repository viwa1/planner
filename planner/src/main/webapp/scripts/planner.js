function initCalendar() {
$(document).ready(function() {
    // page is now ready, initialize the calendar...
    $('#calendar').fullCalendar({
		header : { 
			left: 'prev,next today',
			right: 'month,agendaWeek,agendaDay'
		},
		footer : {
			right: 'title'
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
		defaultView : 'month',
		editable : true,
		events : 'planner/../rest/events'
    });
});
}