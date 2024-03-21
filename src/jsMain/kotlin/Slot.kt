import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.rows
import org.jetbrains.compose.web.dom.*

@Composable
fun Slot() {
    var input by remember { mutableStateOf("") }
    val output by derivedStateOf {
        Avgizer().apply {
            for (line in input.split("\n")) {
                add(line)
            }
        }.analyse()
    }

    Div(
        attrs = {
            classes("row")
        }
    ) {
        // Input
        Div(
            attrs = {
                classes("col-md-6")
            }
        ) {
            Div(
                attrs = {
                    classes("row")
                }
            ) {
                H3 {
                    Text("Input")
                }

            }
            Form {
                Div(attrs = {
                    classes("form-group")
                }) {
                    TextArea(
                        value = input
                    ) {
                        classes("form-control")
                        placeholder(value = "Enter your input here")
                        rows(12)
                        onInput { textInput ->
                            input = textInput.value
                        }
                    }
                }
            }
        }

        // Result
        if (output.isNotEmpty()) {
            Div(
                attrs = {
                    classes("col-md-6")
                }
            ) {
                H3 { Text("Average") }
                Ul {
                    for (node in output) {
                        Li {
                            A(href = node.sdLink) {
                                Text(node.text)
                            }
                        }
                    }
                }
            }
        }
    }
}