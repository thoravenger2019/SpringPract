 var chartDataStr = decodeHtml(chartData);
 var chartJsonArray= JSON.parse(chartDataStr);

 var arrayLength= chartJsonArray.length;

 var numericData= [];
 var labeldata = [];

 for(var i=0; i< arrayLength; i++){
	 numericData[i] = chartJsonArray[i].value;
	 labeldata[i]= chartJsonArray[i].label;
 }
 
 new Chart(document.getElementById("myPieChart"),{
	type:'pie',
	data: {
		labels: labeldata,
		datasets :[{
			label:'My First Dataset',
			backgroundColor: ["#990000","#ff8000","#0080ff"],
			data: numericData
		}]
	},
	options : {
		title: {
			display: true,
			text:'Project Statuses'

		}
	}
});

function decodeHtml(html){
	var txt= document.createElement("textarea");
	txt.innerHTML= html;
	return txt.value;
}