fun String.format(vararg args: Any) {
    this.replace(Regex("{(\\d+)}", RegexOption.MULTILINE)) {
        val number = it.groupValues[0].toInt()
        if (args.size > number) args[number].toString() else ""
    }
}