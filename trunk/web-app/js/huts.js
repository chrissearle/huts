function deleteHutSubmit() {

    if (confirm('Are you sure?')) {
        document.forms['deletemenuform'].submit();
    } else {
        return false;
    }
}