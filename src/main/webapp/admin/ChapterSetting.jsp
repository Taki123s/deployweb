<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>FinDash - Responsive Bootstrap 4 Admin Dashboard Template</title>
    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Typography CSS -->
    <link rel="stylesheet" href="css/typography.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Full calendar -->
    <link href='fullcalendar/core/main.css' rel='stylesheet'/>
    <link href='fullcalendar/daygrid/main.css' rel='stylesheet'/>
    <link href='fullcalendar/timegrid/main.css' rel='stylesheet'/>
    <link href='fullcalendar/list/main.css' rel='stylesheet'/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/flatpickr.min.css">


    <c:url var="MovieList" value="/admin/MovieList"/>
</head>
<body>
<!-- loader Start -->
<div id="loading">
    <div id="loading-center"></div>
</div>
<!-- loader END -->
<!-- Wrapper Start -->
<div class="wrapper">
    <!-- Sidebar  -->
    <c:import url="/admin/sidebar.jsp"/>
    <!-- TOP Nav Bar -->
    <c:import url="/admin/header.jsp"/>
    <div id="content-page" class="content-page">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                            <div class="iq-header-title">
                                <h4 class="card-title">Editable Table</h4>
                            </div>
                        </div>
                        <div class="iq-card-body">
                            <div id="table" class="table-editable">
                              <span class="table-add float-right mb-3 mr-2">
                              <button class="btn btn-sm iq-bg-success"><i
                                      class="ri-add-fill"><span class="pl-1">Add New</span></i>
                              </button>
                              </span>


                                <table class="table table-bordered table-responsive-md table-striped text-center"
                                       id="tableChapter">

                                    <thead>
                                    <tr>
                                        <th>Stt</th>
                                        <th>Chapter</th>
                                        <th>Opening</th>
                                        <th>Review</th>
                                        <th>Option</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="stt" value="0"/>
                                    <c:forEach var="chapter" items="${requestScope.listChapter}">
                                        <c:set var="stt" value="${stt + 1}"/>
                                        <tr>
                                            <td contenteditable="false">${stt}</td>
                                            <td contenteditable="false" id="indexChapter">${chapter.index}</td>
                                            <td contenteditable="false" id="opening">${chapter.opening}</td>

                                            <td>
                                                    ${chapter.name}
                                            </td>
                                            <td>
                                          <span class="table-remove"><button type="button"
                                                                             class="btn iq-bg-danger btn-rounded btn-sm my-0" onclick="removeChapter(${chapter.id},this)">Remove</button>
                                             <span id="settingChapter"><button type="button" class="btn btn-info" value="${chapter.id}" onclick="settingChapter(this)">Setting</button></span>
                                          </span>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="iq-footer">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item"><a href="privacy-policy.html">Privacy
                            Policy</a></li>
                        <li class="list-inline-item"><a href="terms-of-service.html">Terms
                            of Use</a></li>
                    </ul>
                </div>
                <div class="col-lg-6 text-right">
                    Copyright 2020 <a href="#">FinDash</a> All Rights Reserved.
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer END -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">

    <script src="js/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/dataTables.buttons.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/buttons.print.min.js"></script>


    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- jQuery -->
    <script>
        $(document).ready(function () {
            $('#tableChapter').DataTable({
                "searching": true,
                "sorting": true,
                dom: 'Bfrtip',
                buttons: [
                    'copy', 'csv', 'excel', 'pdf', 'print'
                ],
            });

        });

    </script>


    <script>
        function removeChapter(idChapter,button) {
            $.ajax({
                url: "RemoveChapter",
                type: "post",
                data: {
                    idChapter: idChapter,
                },
                success: function (data) {
                let isSuccess = (JSON.parse(data)).isSuccess;
                   if(isSuccess){

                       $('#tableChapter').DataTable().row($($(button).closest('tr'))).remove().draw(false);
                   }
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

        let totalFileCount, fileUploadCount, fileSize;

        function startUploading(button,type) {
            let tr = $(button).closest("tr");
            let tdArray = $(tr).find("td");
            let files = $($(tdArray[3]).find("#video-upload")).prop('files')[0];
            let totalFile = $($(tdArray[3]).find("#video-upload")).prop('files')
            if (files.length === 0) {
                alert("Please choose at least one file and try.");
                return;
            }
            fileUploadCount = 0;
            prepareProgressBarUI(files,tdArray,totalFile);
            uploadFile(tr,type,button);
        }

        function prepareProgressBarUI(files,tdArray,totalFile) {

            totalFileCount = totalFile.length;

            let $tbody =$($(tdArray[3]).find("#progress-bar-container")).find("tbody");
            $tbody.empty();
            $($(tdArray[3]).find("#upload-header-text")).html("uploading");
            for (let i = 0; i < totalFileCount; i++) {
                let fsize = parseFileSize(totalFile[i].size);
                let fname = totalFile[i].name;

                let bar = '<tr id="progress-bar-' + i + '"><td style="width:75%"><div class="filename">' + fname + '</div>'
                    + '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width:0%"></div></div></td>'
                    + '<td  style="width:25%"><span class="size-loaded"></span> ' + fsize + ' <span class="percent-loaded"></span></td></tr>';

                $tbody.append(bar);
            }

            $($(tdArray[3]).find("#upload-status-container")).show();
        }

        function parseFileSize(size) {
            let precision = 1;
            let factor = Math.pow(10, precision);
            size = Math.round(size / 1024); //size in KB
            if (size < 1000) {
                return size + " KB";
            } else {
                size = Number.parseFloat(size / 1024); //size in MB
                if (size < 1000) {
                    return (Math.round(size * factor) / factor) + " MB";
                } else {
                    size = Number.parseFloat(size / 1024); //size in GB
                    return (Math.round(size * factor) / factor) + " GB";
                }
            }
            return 0;
        }

        function uploadFile(tr,type,button) {
            let tdArray = $(tr).find("td");
            let file = $($(tdArray[3]).find("#video-upload")).prop('files')[fileUploadCount];
            fileSize = file.size;
            let fd = new FormData();
            fd.append("video-upload", file);
            let index = $(tr).find("#indexChapter").text();
            let opening = $(tr).find("#opening").text();

            fd.append('index', index);
            fd.append('opening', opening);
            fd.append('type',type);
            if(type==="edit"){
                fd.append("idChapter",$(button).val());
            }
            fd.append('idMovie', ${requestScope.idMovie});
            $.ajax({
                url: '/admin/UpdateChapter',
                type: 'POST',
                data: fd,
                processData: false,
                contentType: false,
                xhr: function () {
                    let myXhr = $.ajaxSettings.xhr();
                    if (myXhr.upload) {
                        myXhr.upload.addEventListener('progress', onUploadProgress.bind(null,tdArray), false);
                    }
                    return myXhr;
                },
                success: function (response) {

                    onUploadComplete(response, false, response.responseText,tr,type,button)
                },
                error: function (xhr, status, error) {
                    let errorMessage = xhr.responseText;
                    console.log(errorMessage);
                    onUploadComplete(xhr, true, errorMessage,tr,type,button);

                },

            });


        }

        function onUploadProgress(tdArray,e) {
            if (e.lengthComputable) {
                let percentComplete = parseInt((e.loaded) * 100 / fileSize);
                let pbar =$($(tdArray[3]).find('#progress-bar-' + fileUploadCount))
                let bar = pbar.find(".progress-bar");
                let sLoaded = pbar.find(".size-loaded");
                let pLoaded = pbar.find(".percent-loaded");
                bar.css("width", percentComplete + '%');
                sLoaded.html(parseFileSize(e.loaded) + " / ");
                pLoaded.html("(" + percentComplete + "%)");
            } else {
                alert('unable to compute');
            }
        }

        function onUploadComplete(e, error, mess,tr,type,button) {
            let tdArray = $(tr).find("td");

            let pbar = $($(tdArray[3]).find('#progress-bar-' + fileUploadCount));

            if (error || e.status === 500) {
                pbar.find(".progress-bar").removeClass("active").addClass("progress-bar-danger");
            } else {
                pbar.find(".progress-bar").removeClass("active");
                pbar.find(".size-loaded").html('<i class="fa fa-check text-success"></i> ');
            }
            fileUploadCount++;
            if (fileUploadCount < totalFileCount) {

                uploadFile(tr,type,button);
                $($(tdArray[3]).find("#upload-header-text")).html((fileUploadCount + 1) + " of " + totalFileCount + " file(s) is uploading");
            } else {
                if (!error) {
                    let data = JSON.parse(e);
                    let chapter = JSON.parse(data.chapter);


                    $($(tdArray[3]).find("#upload-header-text")).html("File(s) uploaded successfully!");
                    let appendInsert = `  <tr>
                                            <td contenteditable="false">1</td>
                                            <td contenteditable="false" id="indexChapter">` + chapter.index + `</td>
                                            <td contenteditable="false" id="opening">` + chapter.opening + `</td>

                                            <td>
                                                        ` + chapter.name + `
                                            </td>
                                            <td>
                                          <span class="table-remove"><button type="button" class="btn iq-bg-danger btn-rounded btn-sm my-0" onclick="removeChapter(`+chapter.id+`,this)">Remove</button>
        <span id="settingChapter"><button type="button" class="btn btn-info" value="`+chapter.id+`" onclick="settingChapter(this)">Setting</button></span>

</span>
                                            </td>
                                        </tr>`;
                    let appendEdit = `
                                            <td contenteditable="false">1</td>
                                            <td contenteditable="false" id="indexChapter">` + chapter.index + `</td>
                                            <td contenteditable="false" id="opening">` + chapter.opening + `</td>

                                            <td>
                                                        ` + chapter.name + `
                                            </td>
                                            <td>
                                          <span class="table-remove"><button type="button" class="btn iq-bg-danger btn-rounded btn-sm my-0" onclick="removeChapter(`+chapter.id+`,this)">Remove</button>
        <span id="settingChapter"><button type="button" class="btn btn-info" value="`+chapter.id+`" onclick="settingChapter(this)">Setting</button></span>

</span>
                                            </td>
                                       `;
                    if(type==="insert"){
                        $("#addChapter").remove();
                        $("tbody").append(appendInsert);
                    }
                    if(type==="edit"){
                        $(tr).html(appendEdit);
                    }

                } else {
                    $($(tdArray[3]).find("#upload-header-text")).html(mess);
                }
            }
        }



        function showHide(ele) {
            if ($(ele).hasClass('fa-window-minimize')) {
                $(ele).removeClass('fa-window-minimize').addClass('fa-window-restore').attr("title", "restore");
                $("#progress-bar-container").slideUp();
            } else {
                $(ele).addClass('fa-window-minimize').removeClass('fa-window-restore').attr("title", "minimize");
                $("#progress-bar-container").slideDown();
            }
        }
    </script>
    <script>

        function settingChapter(button){
            let idChapter = $(button).val();
            let parent = $(button).closest("tr");
            let tdArray = $(parent).find("td");
            $(tdArray[1]).attr("contenteditable","true");
            $(tdArray[2]).attr("contenteditable","true");
            $(tdArray[3]).html(' <input type="file" multiple id="video-upload" name="video-upload" accept="video/*"> <div id="upload-status-container">\n ' +
                ' <div id="upload-header">\n           ' +
                ' <span id="upload-header-text"></span>\n        ' +
                '   \n    ' +
                '    </div>\n       ' +
                ' <div id="progress-bar-container">\n            ' +
                '<table class="table">\n               ' +
                ' <tbody></tbody>\n           ' +
                ' </table>\n       ' +
                ' </div>\n    ' +
                '</div>       \n');
            $(button).css("display","none");
            $($(tdArray[4]).find("#settingChapter")).html(`<button type="button" class="btn btn-info" value="`+idChapter+`" onclick="startUploading(this,'edit')">Submit Setting</button>`)

        }

    </script>
    <!-- Appear JavaScript -->
    <script src="js/jquery.appear.js"></script>
    <!-- Countdown JavaScript -->
    <script src="js/countdown.min.js"></script>
    <!-- Counterup JavaScript -->
    <script src="js/waypoints.min.js"></script>
    <script src="js/jquery.counterup.min.js"></script>
    <!-- Wow JavaScript -->
    <script src="js/wow.min.js"></script>
    <!-- Apexcharts JavaScript -->
    <script src="js/apexcharts.js"></script>
    <!-- Slick JavaScript -->
    <script src="js/slick.min.js"></script>
    <!-- Select2 JavaScript -->
    <script src="js/select2.min.js"></script>
    <!-- Owl Carousel JavaScript -->
    <script src="js/owl.carousel.min.js"></script>
    <!-- Magnific Popup JavaScript -->
    <script src="js/jquery.magnific-popup.min.js"></script>
    <!-- Smooth Scrollbar JavaScript -->
    <script src="js/smooth-scrollbar.js"></script>
    <!-- lottie JavaScript -->
    <script src="js/lottie.js"></script>
    <!-- am core JavaScript -->
    <script src="js/core.js"></script>
    <!-- am charts JavaScript -->
    <script src="js/charts.js"></script>
    <!-- am animated JavaScript -->
    <script src="js/animated.js"></script>
    <!-- am kelly JavaScript -->
    <script src="js/kelly.js"></script>
    <!-- am maps JavaScript -->
    <script src="js/maps.js"></script>
    <!-- am worldLow JavaScript -->
    <script src="js/worldLow.js"></script>
    <!-- Raphael-min JavaScript -->
    <script src="js/raphael-min.js"></script>
    <!-- Morris JavaScript -->
    <script src="js/morris.js"></script>
    <!-- Morris min JavaScript -->
    <script src="js/morris.min.js"></script>
    <!-- Flatpicker Js -->
    <script src="js/flatpickr.js"></script>
    <!-- Style Customizer -->
    <script src="js/style-customizer.js"></script>
    <!-- Chart Custom JavaScript -->
    <script src="js/chart-custom.js"></script>
    <!-- Custom JavaScript -->
    <script src="js/custom.js"></script>
</body>
</html>