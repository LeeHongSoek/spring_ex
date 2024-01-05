
window.onload = function() {
  
  var table = document.querySelector('table.show_endpoint');
  var rows = Array.from(table.rows);
  var groups = rows.reduce((groups, row) => {
    var key = row.cells[0].textContent;
    if (!groups[key]) {
      groups[key] = [];
    }
    groups[key].push(row);
    return groups;
  }, {});

  Object.values(groups).forEach((group, index) => {
    var firstRow = group[0];
    firstRow.cells[0].rowSpan = group.length;
    group.slice(1).forEach(row => {
      row.deleteCell(0);
    });

    var color = index % 2 === 0 ? 'f2f2f2' : '#f5f5f5';
    group.forEach(row => {
      row.style.backgroundColor = color;
    });
  });
  
};