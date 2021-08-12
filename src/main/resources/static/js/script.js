showForm = () => {
    document.getElementById("form").style.display = "";
    cells = document.getElementById("form").children;
    for(var i = 1; i < cells.length - 1; i++ ){
        cells[i].children[0].disabled = false
    }
}

editRow = (element) => {
    document.getElementById("addCustomer").disabled ="disabled";
    form = document.getElementById("form");
    form.style.display = "none";
    parent = element.parentElement.parentElement;
    parent.style.display = "none";

    elements = parent.nextElementSibling.children;
    id = elements[0].innerText;
    document.forms[0].action = `/edit/${id}`;

    for(var i = 1; i < elements.length - 1; i++){
        elements[i].children[0].disabled = false;
        form.children[i].children[0].disabled = true;
    }

    parent.nextElementSibling.style.display = "table-row";
}

toggleName = (element) => {
    if(element.innerText === "See more..."){
        element.innerText = "close";
    } else {
        element.innerText = "See more...";
    }
}