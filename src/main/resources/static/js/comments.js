const postCommentElement = document.getElementById("postComment");
postCommentElement.addEventListener('click', createComment)

function createComment() {
    const routeId = document.getElementById('routeId').value;
    const messageElement = document.getElementById('message');
    const message = messageElement.value;

    fetch("/api/comments", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({routeId, message})
    })
        .then(res => res.json())
        .then(data => {
            const id = data.id;
            const content = data.content;
            const authorName = data.authorName;

            console.log(data)

            const h4Element = document.createElement('h4');
            h4Element.appendChild(document.createTextNode(content))

            const labelElement = document.createElement('label');
            labelElement.appendChild(document.createTextNode(authorName))

            const formElement = document.createElement('form');
            formElement.setAttribute('method', 'POST')
            formElement.setAttribute('action', `/comments/delete/${routeId}/${id}`)

            const buttonElement = document.createElement('button');
            buttonElement.setAttribute('type', 'submit')
            buttonElement.classList.add('btn');
            buttonElement.classList.add('btn-danger');
            buttonElement.appendChild(document.createTextNode('Delete'));

            formElement.appendChild(buttonElement);

            const divElement = document.createElement('div');
            divElement.classList.add('form-group');

            divElement.appendChild(h4Element);
            divElement.appendChild(labelElement);
            divElement.appendChild(formElement);

            const commentsSectionElement = document.getElementById('comments');
            commentsSectionElement.append(divElement);

            messageElement.value = '';
        })
}