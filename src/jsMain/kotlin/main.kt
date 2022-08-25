import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.rows
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Div(
            attrs = {
                classes("container")
            }
        ) {

            // Heading
            Div(
                attrs = {
                    classes("row")
                }
            ) {
                Div(attrs = {
                    classes("col-md-12")
                }) {
                    H1(
                        attrs = {
                            classes("text-center")
                        }
                    ) { Text("/Avgizer/") }
                }
            }

            // Menu
            var slotCount by remember { mutableStateOf(1) }
            Div(
                attrs = {
                    classes("row")
                }
            ) {
                Div(
                    attrs = {
                        classes("col-md-12", "text-right")
                    }
                ) {
                    Button(
                        attrs = {
                            onClick {
                                slotCount++
                            }
                        }
                    ) {
                        Text("Add slot")
                    }
                }
            }

            // Slots
            repeat(slotCount) {
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
                ){
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
                    if(output.isNotEmpty()){
                        Div(
                            attrs = {
                                classes("col-md-6")
                            }
                        ) {
                            H3 { Text("Average") }
                            Ul {
                                for(item in output){
                                    Li {
                                        Text(item)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
