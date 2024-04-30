// https://stackoverflow.com/questions/73529450/how-to-fetch-data-from-api-and-show-it-in-a-table/73529882#73529882

// Accepts the first object from the data and
// returns a string of HTML from its keys
function buildHeadings(obj) {
  const keys = Object.keys(obj);
  return keys.map(key => `<th>${key}</th>`).join('');
}

// Accepts a value data and
// returns an HTML table cell
function buildCell(cell) {
  return `<td>${cell}</td>`;
}

// Accepts an object and
// returns a string of row HTML by mapping
// over the object values and, for each, calling
// `buildCell`
function buildRow(obj) {
  const values = Object.values(obj);
  return `<tr>${values.map(buildCell).join('')}</tr>`;
}

// Accepts the data and
// returns a string of row HTML calling
// `buildRow` for each object in the dataset
function buildRows(data) {
  return data.map(buildRow).join('');
}

// `buildTable` puts it all together and returns
// the final table markup as an HTML string
function buildTable(data) {
  return `
    <table border=1>
      <thead>${buildHeadings(data[0])}</thead>
      <tbody>${buildRows(data)}</tbody>
    </table>
  `;
}

// CUSTOM - CANONIC
function buildCANONICTable(data) {
  return `
    <table border=1>
      <thead>${buildHeadings(data[0])}</thead>
      <tbody>${buildCANONICRows(data)}</tbody>
    </table>
  `;
 }

 function buildCANONICRows(data) {
   //const html;
   //numbers.forEach(myFunction);
   return data[].map(buildRow).join('');
 }

//function myFunction(item) {
//  html += item;
//}