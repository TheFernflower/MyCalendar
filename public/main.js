    var currentEvent = null;

    function createCalendarEvent(title, start, end) {
        $.ajax({
          url: '/events',
          method: 'POST',
          contentType: 'application/json',
          data: JSON.stringify({
              'title': title,
              'start': start,
              'end': end
          })
        }).done(function(data) {
            $('#calendar').fullCalendar( 'refetchEvents' );
        });
    }

    function saveCalendarEvent(calEvent){
        $.ajax({
            url: '/events',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify ({
                'title':calEvent.title,
                'start': calEvent.start,
                'end': calEvent.end,
                'id': calEvent.id
            })
        }).done(function(data){
            $('#calendar').fullCalendar( 'refetchEvents' );
        });
    }

    function deleteCalendarEvent(id){
        $.ajax({
            url: '/events/' + id,
            method: 'DELETE'
        }).done(function(data){
        $('#calendar').fullCalendar( 'refetchEvents' );
        });
     }

     function setCalendarEventCompleted(id){
             $.ajax({
                 url: '/events/' + id + '/completed',
                 method: 'PUT',
             }).done(function(data){
             $('#calendar').fullCalendar( 'refetchEvents' );
             });
         }

    function createTask(title){

        $.ajax({
            url: '/tasks',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                'title': title
            })
        }).done(updateTaskList);
    }

    function deleteTask(id){
        $.ajax({
            url: '/tasks/' + id,
            method: 'DELETE'
        }).done(updateTaskList);
    }



    function setTasksDraggable() {
        $('#tasklist .fc-event').each(function() {

             // store data so the calendar knows to render an event upon drop
             $(this).data('task', {
             title: $.trim($(this).text()), // use the element's text as the event title
             stick: true // maintain when user navigates (see docs on the renderEvent method)
             });

            // make the event draggable using jQuery UI
            $(this).draggable({
             zIndex: 999,
             revert: true,      // will cause the event to go back to its
             revertDuration: 0  //  original position after the drag
            });

            $(this).on('click', taskClickCallback);

      });
    }

    function updateTaskList() {
        $.ajax({

            url: '/tasks',
            method: 'GET',
        }).done(function (data) {

            var divs = '';

            $.each( data, function (key, value) {
                var div = $('<div />', {
                    id: value.id,
                    html: value.title,
                    class: 'fc-event task'
                });
                divs += div.prop('outerHTML');
            });

            $('#tasklist').html(divs);

            setTasksDraggable();
        });
    }

    function showCalendarEventProperties(calEvent){

        $("#title").html(calEvent.title);
        $("#repetition").html(calEvent.repetition);
        var duration = calEvent.end.diff(calEvent.start, 'hours');
        if(duration>0){
             $("#duration-dimension").html("hours");
             $("#duration").html(duration);

        }
        else {
            duration = calEvent.end.diff(calEvent.start, 'minutes');
            $("#duration-dimension").html("min");
            $("#duration").html(duration);
            $("#duration").html();
        }
    }

    function updateCalendarEvent(calEvent){
        $.ajax({
            url: '/events',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify ({
                'title':calEvent.title,
                'start': calEvent.start,
                'end': calEvent.end,
                'id': calEvent.id,
                'repeatable': calEvent.repeatable
            })
        }).done(function(data){
            $('#calendar').fullCalendar( 'refetchEvents' );
        });

    }

    function dropTask(date, jsEvent, ui) {
        createCalendarEvent($(this).data().task.title, date.toDate(), date.add(1, 'hours').toDate());
       // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
            // if so, remove the element from the "Draggable Events" list
            $(this).remove();
        }
    }

    function calendarEventClickCallback(calEvent, jsEvent, view) {
        if(jsEvent.shiftKey){
            var confirmDelete = confirm('Do you really want to remove this event?');
            if(confirmDelete){
                deleteCalendarEvent(calEvent.id);
            }
        }
        else if(jsEvent.altKey){
            alert('You\'ve done something, it\'s so sweet...');
            setCalendarEventCompleted(calEvent.id);
        }
        else {
            showCalendarEventProperties(calEvent);
            currentEvent = calEvent;
        }
    }

    function calendarSelectCallback ( start, end, jsEvent, view ) {
        var title = prompt('Enter the title of the event', '')
        if (title){
            createCalendarEvent(title, start.toDate(), end.toDate());
        }
    }

    function calendarEventChangeCallback (event, jsEvent, ui, view){
        saveCalendarEvent(event);
    }

    function createTaskButtonCallback() {
        var title = prompt('Enter the title of the task', '')
        if (title){
            createTask(title);
        }
     }

     function taskClickCallback(){
        $(this).data().task.title;
        var confirmDelete = confirm('Do you really want to remove this task?');
        if(confirmDelete){
            deleteTask($(this).attr('id'));
        }

     }

     function taskDeleteCallback(){
        var confirmDelete = confirm('Do you really want to remove this task?');
        if(confirmDelete){
            deleteTask(taskClickCallback);
        }
     }

    function saveEventPropertiesCallback() {
        if (currentEvent !== null){
            currentEvent.repeatable = $('#repetition').html();
            if($('#title').html().length > 0){
                currentEvent.title = $('#title').html();
            }
            else alert('Title cannot be empty');

            updateCalendarEvent(currentEvent);
        }

    }

    function deleteCalendarEventCallback(){

            deleteCalendarEvent(currentEvent.id);

    }

    function initCallbacks() {
        $('#button').on('click', createTaskButtonCallback);
        $('#save').on('click', saveEventPropertiesCallback);
        $('#delete').on('click', deleteCalendarEventCallback);

    }

    // on page load
    $(function() {

      // page is now ready, initialize the calendar...

      $('#calendar').fullCalendar({
        //put your options and callbacks here
        header: {
            left: 'month,agendaWeek,agendaDay',
            center: 'title',
            right: 'prev,next,today'}, // buttons for switching between views
        events: '/events',
        selectable: true,
        select: calendarSelectCallback,
        firstDay: 1,
        weekNumbers: true,
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true,
        allDaySlot: false,
        droppable: true, // this allows things to be dropped onto the calendar
        drop: dropTask,
        eventDrop: calendarEventChangeCallback,
        eventResize: calendarEventChangeCallback,
        eventClick: calendarEventClickCallback
      })

      updateTaskList();
      initCallbacks();

    });
